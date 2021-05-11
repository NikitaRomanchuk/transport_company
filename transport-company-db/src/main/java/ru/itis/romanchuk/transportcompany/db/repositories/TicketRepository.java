package ru.itis.romanchuk.transportcompany.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.romanchuk.transportcompany.db.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
