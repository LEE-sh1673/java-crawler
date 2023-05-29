package me.lsh.javacrawler.service.recommend;

import lombok.Getter;
import me.lsh.javacrawler.repository.recommend.dto.RecommendMoimDtoProtection;

@Getter
public class RecommendListMoimDto {

    private final Long id;

    private final String title;

    private final String organizer;

    private final String thumbnail;

    private final String duration;

    private final int viewCount;

    private final int bookmarkCount;

    public RecommendListMoimDto(final RecommendMoimDtoProtection moimDto) {
        this.id = moimDto.getEventId();
        this.title = moimDto.getTitle();
        this.thumbnail = moimDto.getThumbnail();
        this.duration = moimDto.getDuration();
        this.organizer = moimDto.getOrganizer();
        this.viewCount = moimDto.getViewCount();
        this.bookmarkCount = moimDto.getBookmarkCount();
    }
}
