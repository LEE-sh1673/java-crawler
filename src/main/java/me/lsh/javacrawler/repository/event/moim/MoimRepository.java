package me.lsh.javacrawler.repository.event.moim;

import java.util.List;
import java.util.Optional;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.moim.Moim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoimRepository extends JpaRepository<Moim, Long>, MoimCustomRepository  {

    Optional<Moim> findByTitleAndProvider(final String title, final EventProvider provider);

    @Modifying
    @Query("update Moim m SET m.viewCount = m.viewCount + 1 where m.id = :id")
    int updateView(@Param("id") final Long id);

    Page<Moim> findAll(final Pageable pageable);

    @Query("select m from Moim m where m.id in (:ids)")
    Page<Moim> findAllByIds(@Param("ids") final List<Long> ids, final Pageable pageable);

    @Query("select MAX(m.pageId) from Moim m")
    Long findTopByPageId();
}
