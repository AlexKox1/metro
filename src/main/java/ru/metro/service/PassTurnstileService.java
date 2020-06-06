package ru.metro.service;

import org.springframework.stereotype.Service;
import ru.metro.model.PassTurnstile;
import ru.metro.model.Ticket;
import ru.metro.model.Turnstile;
import ru.metro.repository.PassTurnstileRepository;

import java.util.Date;

@Service
public class PassTurnstileService {

    private PassTurnstileRepository passTurnstileRepository;

    public PassTurnstileService(PassTurnstileRepository passTurnstileRepository) {
        this.passTurnstileRepository = passTurnstileRepository;
    }

    public PassTurnstile create(Turnstile turnstile, Ticket ticket, Date date) {
        PassTurnstile passTurnstile = new PassTurnstile();
        passTurnstile.setTicket(ticket);
        passTurnstile.setTurnstile(turnstile);
        passTurnstile.setCreated(date);

        return passTurnstileRepository.save(passTurnstile);
    }
}
