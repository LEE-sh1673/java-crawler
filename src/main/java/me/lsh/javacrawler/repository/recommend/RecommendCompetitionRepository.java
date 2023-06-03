package me.lsh.javacrawler.repository.recommend;

import java.util.List;
import java.util.Optional;
import me.lsh.javacrawler.domain.recommend.FrequentCompetitionAttribute;
import me.lsh.javacrawler.domain.recommend.FrequentSkill;
import me.lsh.javacrawler.domain.recommend.RecommendCompetition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendCompetitionRepository extends JpaRepository<RecommendCompetition, Long> {

    Optional<RecommendCompetition> findByMemberIdAndId(final Long memberId, final Long id);

    @Query("select rc.attributes from RecommendCompetition rc where rc.member.id = :memberId AND rc.id = :recommendId")
    List<FrequentCompetitionAttribute> findAttributesByMemberIdAndId(
        @Param("memberId") final Long memberId,
        @Param("recommendId") final Long recommendId
    );

    @Query("select rc.skills from RecommendCompetition rc where rc.member.id = :memberId AND rc.id = :recommendId")
    List<FrequentSkill> findSkillsByMemberIdAndId(
        @Param("memberId") final Long memberId,
        @Param("recommendId") final Long recommendId
    );

    @Query(value = "select IF(" +
        "exists(" +
            "select * from recommend_competition rc where rc.member_id = :memberId limit 1" +
        "), 'true', 'false')", nativeQuery = true)
    Boolean existsByMemberId(@Param("memberId") long memberId);

    Page<RecommendCompetition> findAllByMemberId(long memberId, final Pageable pageable);
}
