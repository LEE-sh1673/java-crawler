package me.lsh.javacrawler.repository.event.moim;

import java.util.Optional;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.moim.Moim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MoimRepository extends JpaRepository<Moim, Long>, MoimCustomRepository  {

    Optional<Moim> findByTitleAndProvider(final String title, final EventProvider provider);

    Page<Moim> findAll(final Pageable pageable);

    @Query("select MAX(m.pageId) from Moim m")
    Long findTopByPageId();
}
