package me.lsh.javacrawler.repository.event.competition;

import java.util.List;
import java.util.Optional;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.competition.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompetitionRepository extends JpaRepository<Competition, Long>, CompetitionCustomRepository {

    Optional<Competition> findByTitleAndProvider(final String title, final EventProvider provider);

    Page<Competition> findAll(final Pageable pageable);

    @Query( "select c from Competition c where c.id in (:ids)" )
    Page<Competition> findAllByIds(@Param("ids") final List<Long> ids, final Pageable pageable);

    @Query("select c " +
    "from Competition c " +
    "join fetch c.applicants a " +
    "join fetch c.awardBenefits b " +
    "where (:applicant in (a)) " +
    "and (:awardBenefit in (b)) " +
    "and (:awardScale in (c.awardScale))")
    List<Competition> findAllByAttributes(
        @Param("applicant") String applicant,
        @Param("awardBenefit") String awardBenefit,
        @Param("awardScale") String awardScale
    );


    @Query("select c " +
    "from Competition c " +
    "join fetch c.skills skills " +
    "where (:skill in (skills))")
    List<Competition> findAllBySkills(@Param("skill") String skill);


    boolean existsByPageIdAndProvider(final int pageId, final EventProvider provider);

    @Query("select MAX(c.pageId) from Competition c")
    Long findTopByPageId();
}
