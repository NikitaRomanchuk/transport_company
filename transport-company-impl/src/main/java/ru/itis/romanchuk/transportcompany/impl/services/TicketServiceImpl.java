package ru.itis.romanchuk.transportcompany.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.romanchuk.transportcompany.api.dto.TicketDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.TicketForm;
import ru.itis.romanchuk.transportcompany.api.services.TicketService;
import ru.itis.romanchuk.transportcompany.db.models.Passage;
import ru.itis.romanchuk.transportcompany.db.models.Ticket;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.db.repositories.PassageRepository;
import ru.itis.romanchuk.transportcompany.db.repositories.TicketRepository;
import ru.itis.romanchuk.transportcompany.db.repositories.UserRepository;

import java.sql.Timestamp;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private PassageRepository passageRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TicketServiceImpl(TicketRepository ticketRepository, PassageRepository passageRepository) {
        this.ticketRepository = ticketRepository;
        this.passageRepository = passageRepository;
    }

    @Override
    public TicketDto bookPassage(TicketForm ticketForm) {
        Ticket newTicket = Ticket.builder()
                .bookDate(new Timestamp(System.currentTimeMillis()))
                .owner(userRepository.getOne(ticketForm.getUserId()))
                .passage(passageRepository.getOne(ticketForm.getPassageId()))
                .state(Ticket.State.BOOKED)
                .build();
        ticketRepository.save(newTicket);
        return TicketDto.from(newTicket);
    }

    @Override
    public void payForTicket(Long ticketId, User user) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(IllegalArgumentException::new);
        if(!ticket.getOwner().getId().equals(user.getId())){
            throw new IllegalArgumentException();
        }
        ticket.setState(Ticket.State.PAID);
        ticketRepository.save(ticket);
    }

    @Override
    public void returnTicket(Long ticketId, User user) {
        Ticket ticket = ticketRepository.getOne(ticketId);
        if(ticket.getPassage().getStart().getTime() < System.currentTimeMillis() ||
                !ticket.getOwner().getId().equals(user.getId())){
            throw new IllegalArgumentException();
        }
        ticketRepository.deleteById(ticketId);
    }
}
