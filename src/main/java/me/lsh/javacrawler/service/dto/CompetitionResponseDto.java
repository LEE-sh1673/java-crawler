package me.lsh.javacrawler.service.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
public class CompetitionResponseDto {

    private final Long id;

    private final String thumbnail;

    private final int bookmarkCount;

    private final int viewCount;

    private final String title;

    private final String organizer;

    private final Set<Skill> skills;

    private final Set<ApplicantType> applicants;

    private final Set<AwardBenefit> awardBenefits;

    private final String awardAmount;

    private final LocalDateTime startedAt;

    private final LocalDateTime expiredAt;

    private final String originalLink;

    private final String content;

    private final Long dDay;

    private final boolean isExpired;

    public CompetitionResponseDto(final Competition competition) {
        this.id = competition.getId();
        this.thumbnail = competition.getThumbnail();
        this.bookmarkCount = competition.getBookmarkCount();
        this.viewCount = competition.getViewCount();
        this.title = competition.getTitle();
        this.organizer = competition.getOrganizer();
        this.skills = competition.getSkills();
        this.dDay = competition.calculateDDay();
        this.applicants = competition.getApplicants();
        this.awardBenefits = competition.getAwardBenefits();
        this.awardAmount = competition.getAwardAmount();
        this.startedAt = competition.getStartedAt();
        this.expiredAt = competition.getExpiredAt();
        this.originalLink = competition.getOriginalLink();
        this.content = competition.getContent();
        this.isExpired = this.dDay < 0;
    }

    public boolean isNoSkill() {
        return this.skills.contains(Skill.NO_SKILL);
    }
}
