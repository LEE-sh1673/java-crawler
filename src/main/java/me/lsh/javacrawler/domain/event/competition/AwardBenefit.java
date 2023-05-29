package me.lsh.javacrawler.domain.event.competition;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum AwardBenefit {
    COMP_POINT(1, "입사시 가산점"),
    RECRUITMENT(2, "인턴/정규직채용"),
    CERTIFICATE_AWARD(3, "상장 수여"),
    ABOARD_STUDY(4, "해외연수"),
    EXHIBITION(5, "전시기회"),
    DEFAULT(6, "기타");

    private final int code;

    private final String name;

    AwardBenefit(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static AwardBenefit of(final String name) {
        return Arrays.stream(values())
            .filter(awardBenefit -> awardBenefit.matchName(name))
            .findFirst()
            .orElse(AwardBenefit.DEFAULT);
    }

    private boolean matchName(final String name) {
        return name != null && name.contains(this.name);
    }
}
