package me.lsh.javacrawler.domain.event;

import java.time.LocalDateTime;

public enum RecruitmentStatus {
    OPEN, CLOSE;

    public static RecruitmentStatus calStatusByDate(final LocalDateTime endDateTime) {
        if (endDateTime.isAfter(LocalDateTime.now())) {
            return RecruitmentStatus.OPEN;
        }
        return RecruitmentStatus.CLOSE;
    }
}
