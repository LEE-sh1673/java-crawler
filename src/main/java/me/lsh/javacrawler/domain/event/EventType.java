package me.lsh.javacrawler.domain.event;

public enum EventType {
    COMPETITION, MOIM;

    public boolean isCompetition() {
        return this == COMPETITION;
    }

    public boolean isMoim() {
        return this == MOIM;
    }
}
