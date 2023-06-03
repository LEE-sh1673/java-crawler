package me.lsh.javacrawler.repository.recommend;

import java.util.List;
import me.lsh.javacrawler.domain.recommend.FrequentSkill;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentSkillProtection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FrequentSkillRepository extends JpaRepository<FrequentSkill, Long> {

    @Query(value =
        "   SELECT es.event_skills AS skill, COUNT(*) AS count " +
            "   FROM bookmark_event be " +
            "   JOIN member m ON m.member_id = be.member_id " +
            "   JOIN event e ON e.event_id = be.event_id " +
            "   JOIN competition c ON c.event_id = e.event_id " +
            "   JOIN event_skills es ON es.event_id = c.event_id " +
            "   WHERE es.event_skills != 'NO_SKILL' AND m.member_id = :memberId " +
            "   GROUP BY es.event_skills " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 5 ", nativeQuery = true)
    List<MostFrequentSkillProtection> findMostFrequentSkills(
        @Param("memberId") Long memberId
    );
}
