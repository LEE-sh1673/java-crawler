package me.lsh.javacrawler.repository.recommend;

import java.util.List;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentSkillProtection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoimRecommendRepository extends JpaRepository<Moim, Long> {

    @Query(value =
        "SELECT e.event_id AS eventId " +
            "FROM event e " +
            "JOIN moim m ON m.event_id = e.event_id " +
            "JOIN event_skills es ON es.event_id = m.event_id " +
            "JOIN ( " +
            "   SELECT es.event_skills " +
            "   FROM bookmark_event be " +
            "   JOIN member m ON m.member_id = be.member_id " +
            "   JOIN event e ON e.event_id = be.event_id " +
            "   JOIN moim mi ON mi.event_id = e.event_id " +
            "   JOIN event_skills es ON es.event_id = mi.event_id " +
            "   WHERE es.event_skills != 'NO_SKILL' AND m.member_id = :memberId " +
            "   GROUP BY es.event_skills " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 5 " +
            ") AS t ON (es.event_skills) IN (t.event_skills) " +
            "GROUP BY e.title " +
            "ORDER BY m.page_id DESC", nativeQuery = true)
    List<Long> findAllByMostFrequentSkills(@Param("memberId") Long memberId);

    @Query(value=
        "   SELECT es.event_skills AS skill, COUNT(*) AS count "+
            "  FROM bookmark_event be " +
            "   JOIN member m ON m.member_id = be.member_id " +
            "   JOIN event e ON e.event_id = be.event_id " +
            "   JOIN moim mi ON mi.event_id = e.event_id " +
            "   JOIN event_skills es ON es.event_id = mi.event_id " +
            "   WHERE es.event_skills != 'NO_SKILL' AND m.member_id = :memberId " +
            "   GROUP BY es.event_skills " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 5 ", nativeQuery = true)
    List<MostFrequentSkillProtection> findMostFrequentSkills(@Param("memberId") Long memberId);
}
