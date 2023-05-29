package me.lsh.javacrawler.domain.event.competition;

import lombok.Getter;

@Getter
public enum AwardScale {
    UNDER_10_MILLION(1, "1천만원 미만"),
    BETWEEN_10_AND_30_MILLION(2, "1천만원 이상 3천만원 미만"),
    BETWEEN_30_AND_50_MILLION(3, "3천만원 이상 5천만원 미만"),
    OVER_50_MILLION(4, "5천만원 이상"),
    NO_AWARD(5, "상금 없음");

    private final int code;

    private final String name;

    AwardScale(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    public static AwardScale classifyScale(final String awardAmountStr) {

        if ("-".equals(awardAmountStr)) {
            return AwardScale.NO_AWARD;
        }
        long prizeAmount = parseAwardAmount(awardAmountStr);

        if (prizeAmount < 10_000_000L) {
            return AwardScale.UNDER_10_MILLION;
        } else if (prizeAmount < 30_000_000L) {
            return AwardScale.BETWEEN_10_AND_30_MILLION;
        } else if (prizeAmount < 50_000_000L) {
            return AwardScale.BETWEEN_30_AND_50_MILLION;
        } else {
            return AwardScale.OVER_50_MILLION;
        }
    }

    private static long parseAwardAmount(String awardAmountStr) {
        String[] tokens = awardAmountStr.replaceAll("원", "").split("\\s+");
        long billion = 0L;
        long million = 0L;

        for (String token : tokens) {
            long value = Long.parseLong(token.replaceAll("[억|만]", ""));

            if (token.endsWith("억")) {
                billion += value * 100_000_000L;
            } else if (token.endsWith("만")) {
                million += value * 10_000L;
            } else {
                million += value;
            }
        }
        return billion + million;
    }
}

