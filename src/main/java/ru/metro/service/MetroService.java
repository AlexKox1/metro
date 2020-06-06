package ru.metro.service;

import org.springframework.stereotype.Service;
import ru.metro.model.Ticket;
import ru.metro.model.Turnstile;

import java.util.Calendar;
import java.util.List;

@Service
public class MetroService {

    private PassTurnstileService passTurnstileService;
    private TicketService ticketService;
    private TurnstileService turnstileService;

    public MetroService(PassTurnstileService passTurnstileService, TicketService ticketService, TurnstileService turnstileService) {
        this.passTurnstileService = passTurnstileService;
        this.ticketService = ticketService;
        this.turnstileService = turnstileService;
    }

    public List<Turnstile> getTurnstiles() {
        return turnstileService.getAll();
    }

    public Turnstile getTurnstile(Long id) {
        Turnstile turnstile = turnstileService.find(id);
        if (turnstile.isEnabled() && (Calendar.getInstance().getTime().getTime() - turnstile.getUpdated().getTime() >= 5*1000)) {
            turnstile.setEnabled(false);
            return turnstileService.update(turnstile);
        }
        return turnstile;
    }

    public List<Ticket> getTickets() {
        return ticketService.getAll();
    }

    public Turnstile createTurnstile() {
        return turnstileService.createTurnstile();
    }

    public Ticket createTicket(Integer quantity) {
        return ticketService.createTicket(quantity);
    }

    public boolean pass(Long ticketId, Long turnstileId) {
        Ticket ticket = ticketService.find(ticketId);
        if (ticket == null) {
            return false;
        }
        if (ticket.getQuantity() <= 0) {
            return false;
        }
        if (Calendar.getInstance().getTime().getTime() >= ticket.getClosed().getTime()) {
            return false;
        }
        ticket.setQuantity(ticket.getQuantity() - 1);
        ticketService.update(ticket);

        Calendar passDate = Calendar.getInstance();

        Turnstile turnstile = turnstileService.find(turnstileId);
        turnstile.setEnabled(true);
        turnstile.setQuantity(turnstile.getQuantity() + 1);
        turnstile.setUpdated(passDate.getTime());
        turnstileService.update(turnstile);

        passTurnstileService.create(turnstile, ticket, passDate.getTime());

        return true;
    }


    public boolean go(Long turnstileId) {
        Turnstile turnstile = turnstileService.find(turnstileId);
        if ((Calendar.getInstance().getTime().getTime() - turnstile.getUpdated().getTime()) < 5*1000) {
            return true;
        } else {
            turnstile.setEnabled(false);
            turnstileService.update(turnstile);
            return false;
        }
    }
}
