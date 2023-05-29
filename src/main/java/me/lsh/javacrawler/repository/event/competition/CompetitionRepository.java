package me.lsh.javacrawler.repository.event.competition;

import java.util.Optional;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.competition.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetitionRepository extends JpaRepository<Competition, Long>, CompetitionCustomRepository {

    Optional<Competition> findByTitleAndProvider(final String title, final EventProvider provider);

    Page<Competition> findAll(final Pageable pageable);

    boolean existsByPageIdAndProvider(final int pageId, final EventProvider provider);

    @Query("select MAX(c.pageId) from Competition c")
    Long findTopByPageId();
}
