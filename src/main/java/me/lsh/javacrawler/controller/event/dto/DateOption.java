package me.lsh.javacrawler.controller.event.dto;

import lombok.Getter;

@Getter
public enum DateOption {
    ALL(1, "모든날"),
    TODAY(2, "오늘"),
    THIS_WEEK(3, "이번주"),
    THIS_MONTH(4, "이번달"),
    CLOSED(5, "모집 종료");

    private final int id;

    private final String label;

    DateOption(final int id, final String label) {
        this.id = id;
        this.label = label;
    }
}
