package ru.metro.service;

import org.springframework.stereotype.Service;
import ru.metro.model.Ticket;
import ru.metro.repository.TicketRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket find(Long id) {
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    public Ticket createTicket(Integer quantity) {
        Ticket ticket = new Ticket();
        ticket.setQuantity(quantity);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        ticket.setClosed(calendar.getTime());
        return ticketRepository.save(ticket);
    }

    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

}
