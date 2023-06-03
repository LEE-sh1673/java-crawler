package me.lsh.javacrawler.controller.recommend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import lombok.Getter;
import me.lsh.javacrawler.domain.recommend.RecommendCompetition;

@Getter
public class RecommendCompetitionsListResponseDto {

    private static final String TITLE_FORMAT = " 레포트";

    private final Long id;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final String title;

    public RecommendCompetitionsListResponseDto(final RecommendCompetition competition) {
        LocalDateTime createdDateTime = competition.getCreatedAt();
        this.startDate = createdDateTime.minusDays(7).toLocalDate();
        this.endDate = createdDateTime.toLocalDate();
        this.title = formatTitlePrefix(startDate) + TITLE_FORMAT;
        this.id = competition.getId();
    }

    private String formatTitlePrefix(final LocalDate startDate) {
        int startMonth = startDate.getMonthValue();
        int startWeek = startDate.get(WeekFields.ISO.weekOfMonth());
        int endMonth = endDate.getMonthValue();

        if (startMonth == endMonth) {
            return String.format("%d월 %d주차", startMonth, startWeek);
        }
        int endWeek = startDate.get(WeekFields.ISO.weekOfMonth());
        return String.format("%d월 %d주차 ~ %d월 1주차", startMonth, endWeek, endMonth);
    }
}
