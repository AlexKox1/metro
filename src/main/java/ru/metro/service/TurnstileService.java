package ru.metro.service;

import org.springframework.stereotype.Service;
import ru.metro.model.Turnstile;
import ru.metro.repository.TurnstileRepository;

import java.util.List;

@Service
public class TurnstileService {

    private TurnstileRepository turnstileRepository;

    public TurnstileService(TurnstileRepository turnstileRepository) {
        this.turnstileRepository = turnstileRepository;
    }

    public Turnstile find(Long turnstileId) {
        return turnstileRepository.findById(turnstileId).get();
    }

    public List<Turnstile> getAll() {
        return turnstileRepository.findAll();
    }

    public Turnstile createTurnstile() {
        Turnstile turnstile = new Turnstile();
        return turnstileRepository.save(turnstile);
    }

    public Turnstile update(Turnstile turnstile) {
        return turnstileRepository.save(turnstile);
    }
}
