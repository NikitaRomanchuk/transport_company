package ru.itis.romanchuk.transportcompany.api.services;

import ru.itis.romanchuk.transportcompany.api.dto.TicketDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.TicketForm;
import ru.itis.romanchuk.transportcompany.db.models.User;

public interface TicketService {
    TicketDto bookPassage(TicketForm ticketForm);

    void payForTicket(Long ticketId, User user);

    void returnTicket(Long ticketId, User user);

}
