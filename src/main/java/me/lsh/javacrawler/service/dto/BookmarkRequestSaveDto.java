package me.lsh.javacrawler.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkRequestSaveDto {

    private Long memberId;

    private Long eventId;

    @Override
    public String toString() {
        return "BookmarkRequestSaveDto{" +
            "memberId=" + memberId +
            ", eventId=" + eventId +
            '}';
    }
}
