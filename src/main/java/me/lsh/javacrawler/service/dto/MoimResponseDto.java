package me.lsh.javacrawler.service.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.event.moim.MoimType;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
public class MoimResponseDto {

    private final Long id;

    private final String thumbnail;

    private final int bookmarkCount;

    private final int viewCount;

    private final String title;

    private final String organizer;

    private final Set<Skill> skills;

    private final Set<ApplicantType> applicants;

    private final LocalDateTime startedAt;

    private final LocalDateTime expiredAt;

    private final String originalLink;

    private final String content;

    private final Long dDay;

    private final boolean isExpired;

    private final String location;

    private final MoimType moimType;

    private final String duration;

    private final boolean feeRequired;

    public MoimResponseDto(final Moim moim) {
        this.id = moim.getId();
        this.thumbnail = moim.getThumbnail();
        this.bookmarkCount = moim.getBookmarkCount();
        this.viewCount = moim.getViewCount();
        this.title = moim.getTitle();
        this.organizer = moim.getOrganizer();
        this.skills = moim.getSkills();
        this.applicants = moim.getApplicants();
        this.startedAt = moim.getStartedAt();
        this.expiredAt = moim.getExpiredAt();
        this.originalLink = moim.getOriginalLink();
        this.content = moim.getContent();
        this.dDay = moim.calculateDDay();
        this.isExpired = this.dDay < 0;
        this.location = moim.getLocation();
        this.moimType = moim.getMoimType();
        this.duration = moim.getDuration();
        this.feeRequired = moim.isFeeRequired();
    }

    public boolean isNoSkill() {
        return this.skills.contains(Skill.NO_SKILL);
    }
}
