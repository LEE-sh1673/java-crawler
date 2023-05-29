package me.lsh.javacrawler.domain.event.moim;

import lombok.Getter;

@Getter
public enum MoimType {
    ONLINE("온라인"),
    OFFLINE("오프라인");

    private final String name;

    MoimType(final String name) {
        this.name = name;
    }
}
