package me.lsh.javacrawler.repository.recommend.dto;

import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.AwardScale;

public interface FrequentCompetitionAttributeProtection {

    ApplicantType getApplicant();

    AwardScale getAwardScale();

    AwardBenefit getAwardBenefit();

    int getCount();
}
