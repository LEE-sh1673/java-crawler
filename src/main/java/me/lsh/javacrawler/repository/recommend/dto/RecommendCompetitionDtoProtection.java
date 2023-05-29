package me.lsh.javacrawler.repository.recommend.dto;

import me.lsh.javacrawler.domain.event.competition.AwardScale;

public interface RecommendCompetitionDtoProtection {

    Long getEventId();

    String getTitle();

    String getOrganizer();

    String getThumbnail();

    String getAwardAmount();

    AwardScale getAwardScale();

    int getBookmarkCount();

    int getViewCount();
}
