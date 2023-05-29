package me.lsh.javacrawler.parser.event.competition.linkareer;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.AwardScale;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Slf4j
public class AttributeElementParser {

    private static final String ATTRIBUTE_SELECTOR = "div > div:nth-child(2) > div:nth-child(2) h3";

    private static final String COLLECTION_SPLITTER = ", ";

    private static final int APPLICANTS_POSITION = 1;

    private static final int AWARD_AMOUNT_POSITION = 2;

    private static final int DURATION_POSITION = 3;

    private static final int AWARD_BENEFITS_POSITION = 5;

    private final Elements elements;

    public AttributeElementParser(final Document document) {
        this.elements = document.select(ATTRIBUTE_SELECTOR);
    }

    public Set<ApplicantType> parseApplicantTypes() {
        String applicants = elements.get(APPLICANTS_POSITION).text();
        Set<ApplicantType> applicantTypes = parseToSet(applicants, ApplicantType::of);
        log.info("관련 기술 파싱 = {}", applicantTypes);
        return applicantTypes;
    }

    public Set<AwardBenefit> parseAwardBenefits() {
        String benefits = elements.get(AWARD_BENEFITS_POSITION).text();
        Set<AwardBenefit> awardBenefits = parseToSet(benefits, AwardBenefit::of);
        log.info("활동 혜택 파싱 = {}", awardBenefits);
        return parseToSet(benefits, AwardBenefit::of);
    }

    private <T> Set<T> parseToSet(final String collections, final
    Function<? super String, ? extends T> mapper) {
        return Arrays.stream(collections.split(COLLECTION_SPLITTER))
            .map(mapper)
            .collect(Collectors.toSet());
    }

    public AwardScale parseAwardScale() {
        String awardAmount = elements.get(AWARD_AMOUNT_POSITION).text();
        return AwardScale.classifyScale(awardAmount);
    }

    public String parseAwardAmount() {
        String awardAmount = elements.get(AWARD_AMOUNT_POSITION).text();
        log.info("시상 규모 파싱 = {}", awardAmount);
        return awardAmount;
    }

    public LocalDateTime parseStartedAt() {
        LocalDateTime startedAt = parseLocalDateTime(parseDuration()[0], LocalTime.MIN);
        log.info("\t-> 접수 시작 기간 파싱 = {}", startedAt);
        return startedAt;
    }

    public LocalDateTime parseExpiredAt() {
        LocalDateTime expiredAt = parseLocalDateTime(parseDuration()[1], LocalTime.MAX);
        log.info("\t-> 접수 미감 기간 파싱 = {}", expiredAt);
        return expiredAt;
    }

    public String[] parseDuration() {
        String duration = elements.get(DURATION_POSITION).text();
        log.info("접수 기간 파싱 = {}", duration);
        return duration.split(" ~ ");
    }

    private LocalDateTime parseLocalDateTime(final String dateStr, final LocalTime localTime) {
        try {
            LocalDate localDate = LocalDate.parse(dateStr, ofPattern("yy.M.d"));
            return localDate.atTime(localTime);
        } catch (DateTimeParseException e) {
            throw new IllegalStateException("Can not parse " + dateStr);
        }
    }
}
