package me.lsh.javacrawler.domain.event.competition;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AwardScaleTest {

    @Test
    void 시상규모_1천만미만() {
        assertEquals(AwardScale.UNDER_10_MILLION, AwardScale.classifyScale("50만 원"));
        assertEquals(AwardScale.UNDER_10_MILLION, AwardScale.classifyScale("5만 원"));
        assertEquals(AwardScale.UNDER_10_MILLION, AwardScale.classifyScale("5만원"));
    }

    @Test
    void 시상규모_1천이상_3천미만() {
        assertEquals(AwardScale.BETWEEN_10_AND_30_MILLION, AwardScale.classifyScale("1000만원"));
        assertEquals(AwardScale.BETWEEN_10_AND_30_MILLION, AwardScale.classifyScale("1499만원"));
        assertEquals(AwardScale.BETWEEN_10_AND_30_MILLION, AwardScale.classifyScale("2999만 원"));
    }

    @Test
    void 시상규모_5천이상() {
        assertEquals(AwardScale.OVER_50_MILLION, AwardScale.classifyScale("5000만원"));
        assertEquals(AwardScale.OVER_50_MILLION, AwardScale.classifyScale("1억 원"));
        assertEquals(AwardScale.OVER_50_MILLION, AwardScale.classifyScale("642억원"));
    }
}