package ru.metro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.metro.model.PassTurnstile;

@Repository
public interface PassTurnstileRepository extends JpaRepository<PassTurnstile, Long> {

}
