package me.lsh.javacrawler.repository.event;

import me.lsh.javacrawler.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
