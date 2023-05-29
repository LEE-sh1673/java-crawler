package me.lsh.javacrawler.service.dto;

import java.util.Set;
import lombok.Getter;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
public class CompetitionListResponseDto {

    private final Long id;

    private final String thumbnail;

    private final int bookmarkCount;

    private final int viewCount;

    private final String title;

    private final String organizer;

    private final Set<Skill> skills;

    private final Long dDay;

    private final boolean isExpired;

    private final Set<ApplicantType> applicants;

    public CompetitionListResponseDto(final Competition competition) {
        this.id = competition.getId();
        this.thumbnail = competition.getThumbnail();
        this.bookmarkCount = competition.getBookmarkCount();
        this.viewCount = competition.getViewCount();
        this.title = competition.getTitle();
        this.organizer = competition.getOrganizer();
        this.skills = competition.getSkills();
        this.dDay = competition.calculateDDay();
        this.isExpired = this.dDay < 0;
        this.applicants = competition.getApplicants();
    }

    @Override
    public String toString() {
        return "CompetitionListResponseDto{" +
            "id=" + id +
            ", thumbnail='" + thumbnail + '\'' +
            ", bookmarkCount=" + bookmarkCount +
            ", viewCount=" + viewCount +
            ", title='" + title + '\'' +
            ", organizer='" + organizer + '\'' +
            ", skills=" + skills +
            ", dDay=" + dDay +
            ", isExpired=" + isExpired +
            ", applicants=" + applicants +
            '}';
    }
}
