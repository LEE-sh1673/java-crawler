package me.lsh.javacrawler.domain.member;

import lombok.Getter;

@Getter
public enum Job {
    STUDENT("학생"),
    BACKEND("백엔드 엔지니어"),
    FRONTEND("프론트엔드 엔지니어"),
    DATA_SCIENTIST("데이터 엔지니어"),
    FULLSTACK("풀스택 엔지니어"),
    UI_UX("UI/UX 디자이너"),
    NO_JOB("-");

    private final String name;

    Job(final String name) {
        this.name = name;
    }
}
