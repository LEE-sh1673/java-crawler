package me.lsh.javacrawler.repository.recommend.dto;

import lombok.Getter;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.AwardScale;
import me.lsh.javacrawler.domain.recommend.FrequentCompetitionAttribute;

@Getter
public class FrequentCompetitionAttributeDto {

    private final ApplicantType applicant;

    private final AwardScale awardScale;

    private final AwardBenefit awardBenefit;

    private final int count;

    public FrequentCompetitionAttributeDto(final FrequentCompetitionAttribute attribute) {
        this.applicant = attribute.getApplicant();
        this.awardScale = attribute.getAwardScale();
        this.awardBenefit = attribute.getAwardBenefit();
        this.count = attribute.getCount();
    }
}
