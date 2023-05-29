package me.lsh.javacrawler.repository.recommend;

import java.util.List;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentCompetitionAttribute;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentSkill;
import me.lsh.javacrawler.repository.recommend.dto.RecommendCompetitionDtoProtection;
import me.lsh.javacrawler.repository.recommend.dto.RecommendSkillCompetitionDtoProtection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendCompetitionRepository extends JpaRepository<Competition, Long> {

    @Query(value =
        "SELECT e.event_id AS eventId, c.page_id AS pageId, e.title, " +
            "e.thumbnail, c.award_scale AS awardScale, " +
            "c.award_amount AS awardAmount, e.organizer, " +
            "e.bookmark_count AS bookmarkCount, e.view_count AS viewCount  " +
            "FROM event AS e " +
            "JOIN competition AS c ON c.event_id = e.event_id " +
            "JOIN competition_benefit cb ON cb.event_id = c.event_id " +
            "JOIN event_applicants ea ON ea.event_id = c.event_id " +
            "JOIN ( " +
            "   SELECT ea.applicants, c.award_scale, cb.benefits, COUNT(*) AS cnt" +
            "   FROM bookmark_event be" +
            "   JOIN member m ON m.member_id = be.member_id" +
            "   JOIN event e ON e.event_id = be.event_id" +
            "   JOIN competition c ON c.event_id = e.event_id" +
            "   JOIN competition_benefit cb ON cb.event_id = c.event_id" +
            "   JOIN event_applicants ea ON ea.event_id = c.event_id " +
            "   WHERE m.member_id = :memberId " +
            "   GROUP BY ea.applicants, c.award_scale, cb.benefits " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 1 " +
            ") AS t ON c.award_scale = t.award_scale AND (cb.benefits) IN (t.benefits) " +
            "AND ea.applicants = t.applicants " +
            "GROUP BY e.event_id " +
            "ORDER BY c.page_id DESC", nativeQuery = true)
    List<RecommendCompetitionDtoProtection> findMostFrequentCompetitionsByAwardsAndBenefits(
        @Param("memberId") Long memberId
    );

    @Query(value =
        "   SELECT ea.applicants AS applicant, c.award_scale AS awardScale, cb.benefits AS awardBenefit" +
            "   FROM bookmark_event be" +
            "   JOIN member m ON m.member_id = be.member_id" +
            "   JOIN event e ON e.event_id = be.event_id" +
            "   JOIN competition c ON c.event_id = e.event_id" +
            "   JOIN competition_benefit cb ON cb.event_id = c.event_id" +
            "   JOIN event_applicants ea ON ea.event_id = c.event_id " +
            "   WHERE m.member_id = :memberId " +
            "   GROUP BY ea.applicants, c.award_scale, cb.benefits " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 1 ", nativeQuery = true)
    MostFrequentCompetitionAttribute findMostFrequentCompetitionAttribute(
        @Param("memberId") Long memberId
    );

    @Query(value =
        "SELECT e.event_id AS eventId, e.title, " +
            "e.thumbnail, c.award_amount AS awardAmount, " +
            "c.award_scale AS awardScale, e.organizer, " +
            "e.bookmark_count AS bookmarkCount, e.view_count AS viewCount  " +
            "FROM event e " +
            "JOIN competition c ON c.event_id = e.event_id " +
            "JOIN event_skills es ON es.event_id = c.event_id " +
            "JOIN ( " +
            "   SELECT es.event_skills " +
            "   FROM bookmark_event be " +
            "   JOIN member m ON m.member_id = be.member_id " +
            "   JOIN event e ON e.event_id = be.event_id " +
            "   JOIN competition c ON c.event_id = e.event_id " +
            "   JOIN event_skills es ON es.event_id = c.event_id " +
            "   WHERE es.event_skills != 'NO_SKILL' AND m.member_id = :memberId " +
            "   GROUP BY es.event_skills " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 5 " +
            ") AS t ON (es.event_skills) IN (t.event_skills) " +
            "GROUP BY e.title " +
            "ORDER BY c.page_id DESC", nativeQuery = true)
    List<RecommendCompetitionDtoProtection> findCompetitionsByFrequentSkills(
        @Param("memberId") Long memberId);


    @Query(value=
        "   SELECT es.event_skills AS skill"+
            "   FROM bookmark_event be " +
            "   JOIN member m ON m.member_id = be.member_id " +
            "   JOIN event e ON e.event_id = be.event_id " +
            "   JOIN competition c ON c.event_id = e.event_id " +
            "   JOIN event_skills es ON es.event_id = c.event_id " +
            "   WHERE es.event_skills != 'NO_SKILL' AND m.member_id = :memberId " +
            "   GROUP BY es.event_skills " +
            "   ORDER BY COUNT(*) DESC " +
            "   LIMIT 5 ", nativeQuery = true)
    List<MostFrequentSkill> findMostFrequentSkillInBookmark(@Param("memberId") Long memberId);
}
