package me.lsh.javacrawler.service.dto;

import java.util.Set;
import lombok.Getter;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.AwardScale;
import me.lsh.javacrawler.domain.event.competition.Competition;

@Getter
public class CompetitionUpdateDto extends EventUpdateDto {

    private String awardAmount;

    private AwardScale awardScale;

    private Set<AwardBenefit> awardBenefit;

    public CompetitionUpdateDto(final Competition competition) {
        super(competition);
        this.awardAmount = competition.getAwardAmount();
        this.awardScale = competition.getAwardScale();
        this.awardBenefit = competition.getAwardBenefits();
    }
}
