package me.lsh.javacrawler.repository.recommend;

import java.util.List;
import me.lsh.javacrawler.domain.recommend.FrequentCompetitionAttribute;
import me.lsh.javacrawler.repository.recommend.dto.FrequentCompetitionAttributeProtection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendCompetitionAttributesRepository extends
    JpaRepository<FrequentCompetitionAttribute, Long> {

    @Query(value =
        "   SELECT ea.applicants AS applicant, c.award_scale AS awardScale, " +
            "cb.benefits AS awardBenefit, COUNT(*) AS count " +
            "   FROM bookmark_event be" +
            "   JOIN member m ON m.member_id = be.member_id" +
            "   JOIN event e ON e.event_id = be.event_id" +
            "   JOIN competition c ON c.event_id = e.event_id" +
            "   JOIN competition_benefit cb ON cb.event_id = c.event_id" +
            "   JOIN event_applicants ea ON ea.event_id = c.event_id " +
            "   WHERE m.member_id = :memberId " +
            "   AND (be.created_at BETWEEN SYSDATE() - INTERVAL 7 DAY AND SYSDATE()) " +
            "   GROUP BY ea.applicants, c.award_scale, cb.benefits " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 5 ", nativeQuery = true)
    List<FrequentCompetitionAttributeProtection> findFrequentAttributes(
        @Param("memberId") Long memberId
    );
}
