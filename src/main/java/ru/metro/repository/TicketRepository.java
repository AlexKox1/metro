package ru.metro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.metro.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
