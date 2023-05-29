package me.lsh.javacrawler.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import lombok.Getter;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.skill.Skill;


@Getter
public class MoimListResponseDto {

    private static final DateTimeFormatter DATE_TIME_FORMATTER
        = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a HH:mm");

    private final Long id;

    private final String thumbnail;

    private final int bookmarkCount;

    private final int viewCount;

    private final String title;

    private final String organizer;

    private final Set<Skill> skills;

    private final String holdingDate;

    private final boolean isFeeRequired;

    public MoimListResponseDto(final Moim moim) {
        this.id = moim.getId();
        this.thumbnail = moim.getThumbnail();
        this.bookmarkCount = moim.getBookmarkCount();
        this.viewCount = moim.getViewCount();
        this.title = moim.getTitle();
        this.organizer = moim.getOrganizer();
        this.skills = moim.getSkills();
        this.holdingDate = toHoldingDateFormat(moim.getExpiredAt());
        this.isFeeRequired = moim.isFeeRequired();
    }

    public String toHoldingDateFormat(final LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
