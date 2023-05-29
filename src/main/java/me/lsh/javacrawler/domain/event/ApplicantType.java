package me.lsh.javacrawler.domain.event;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum ApplicantType {
    NO_LIMIT(1, "제한 없음"),
    UNIV_STUDENT(2, "대학생"),
    OFFICE_WORKER(3, "직장인/일반인"),
    TEENAGER(4, "청소년");

    private final int code;

    private final String name;

    ApplicantType(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static ApplicantType of(final String applicant) {
        return Arrays.stream(values())
            .filter(applicantType -> applicantType.matchName(applicant))
            .findFirst()
            .orElse(ApplicantType.NO_LIMIT);
    }

    private boolean matchName(final String applicant) {
        return applicant != null && applicant.contains(this.name);
    }
}
