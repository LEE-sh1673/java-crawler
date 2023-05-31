package me.lsh.javacrawler.parser.event.moim.festa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Slf4j
public class RootElementParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER
        = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a hh:mm");

    private static final String DURATION_SPLITTER = " - ";

    private static final String PAGE_ROOT_SELECTOR = ".Container-sc-1mgur4j-0:eq(0)";

    private final Elements elements;

    public RootElementParser(final Document document) {
        this.elements = document.select(PAGE_ROOT_SELECTOR);
    }

    public String parseThumbnail() {
        String thumbnail = elements
            .select("div.EventInfoPage__MainImage-sc-1ya0yur-2").attr("src");
        log.info("썸네일 파싱 = {}", thumbnail);
        return thumbnail;
    }

    public String parseTitle() {
        String title = elements.select("div.PrimaryEventInfo__Wrapper-sc-86u3sj-0")
            .select("h1").text();
        log.info("제목 파싱 = {}", title);
        return title;
    }

    public String parseLocation() {
        String location = elements.select("div.PrimaryEventInfo__VenueText-sc-86u3sj-2").text();
        log.info("장소 파싱 = {}", location);
        return location.replace("at ", "");
    }

    public LocalDateTime parseStartedAt() throws WebCrawlerParsingException {
        String duration = parseDuration().split(DURATION_SPLITTER)[0];
        LocalDateTime startedAt = parseLocalDateTime(duration);
        log.info("\t -> 접수 시작 기간 파싱 = {}", startedAt);
        return startedAt;
    }

    public LocalDateTime parseExpiredAt() throws WebCrawlerParsingException {
        String duration = parseDuration().split(DURATION_SPLITTER)[0];
        LocalDateTime expiredAt = parseLocalDateTime(duration);
        log.info("\t -> 접수 마감 기간 파싱 = {}", expiredAt);
        return expiredAt;
    }

    public String parseDuration() {
        String duration = elements.select(".PrimaryEventInfo__DateInfo-sc-86u3sj-3 div:eq(1)").text();
        log.info("기간 파싱 = {}", duration);
        return duration;
    }

    private LocalDateTime parseLocalDateTime(final String dateStr) {
        try {
            return LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalStateException("Can not parse datetime. " + dateStr);
        }
    }

    public String parseOrganizer() {
        String organizer
            = elements.select("div.PrimaryEventInfo__HostText-sc-86u3sj-10").text();
        log.info("주최기관 파싱 = {}", organizer);
        return organizer;
    }

    public boolean parseFeeRequired() {
        String fee = elements.select("span.tickets__PriceSpan-sc-1d0zp6o-4:eq(0)").text();
        log.info("비용 파싱 = {}", fee);
        return !"무료".equals(fee);
    }

    public String parseContent() {
        return elements.select(".UserContentArea-sc-1w8buon-0:eq(0)").text();
    }

    public String parseContentHTML() {
        return elements.select(".UserContentArea-sc-1w8buon-0:eq(0)").outerHtml();
    }
}
