package me.lsh.javacrawler.domain.recommend;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lsh.javacrawler.common.domain.BaseTimeEntity;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FrequentSkill extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Skill skill;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommend_competition_id")
    private RecommendCompetition recommendCompetition;

    @Builder
    public FrequentSkill(Skill skill, int count) {
        this.skill = skill;
        this.count = count;
    }

    public void setRecommendCompetition(
        RecommendCompetition recommendCompetition) {
        this.recommendCompetition = recommendCompetition;
    }

    public String getSkillName() {
        return skill.name();
    }
}
