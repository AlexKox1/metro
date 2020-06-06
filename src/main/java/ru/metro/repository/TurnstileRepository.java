package ru.metro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.metro.model.Turnstile;

@Repository
public interface TurnstileRepository extends JpaRepository<Turnstile, Long> {
}
