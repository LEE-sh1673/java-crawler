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
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.AwardScale;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FrequentCompetitionAttribute extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicantType applicant;

    @Enumerated(EnumType.STRING)
    private AwardScale awardScale;

    @Enumerated(EnumType.STRING)
    private AwardBenefit awardBenefit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommend_competition_id")
    private RecommendCompetition recommendCompetition;

    private int count;

    @Builder
    public FrequentCompetitionAttribute(ApplicantType applicant,
        AwardScale awardScale, AwardBenefit awardBenefit, int count) {
        this.applicant = applicant;
        this.awardScale = awardScale;
        this.awardBenefit = awardBenefit;
        this.count = count;
    }

    public void setRecommendCompetition(final RecommendCompetition recommendCompetition) {
        this.recommendCompetition = recommendCompetition;
    }

    public String getApplicantName() {
        return applicant.name();
    }

    public String getAwardScaleName() {
        return awardScale.name();
    }

    public String getAwardBenefitName() {
        return awardBenefit.name();
    }
}
