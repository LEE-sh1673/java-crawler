package me.lsh.javacrawler.parser.event.moim.festa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.stream.Collectors;
import me.lsh.javacrawler.parser.event.MoimLinkParser;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class FestaLinkParser implements MoimLinkParser {

    private static final String LIST_ROOT_SELECTOR
        = ".EventListPage__StyledContainer-sc-3g4vsg-0 div.sc-bdVaJa.fWoBlI";

    private static final DateTimeFormatter DATE_TIME_FORMATTER
        = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h:mm");

    @Override
    public Set<Integer> parseLinks(final Document document) {
        Set<Integer> links = parseLinksFrom(document);

        if (links.size() == 0) {
            throw new WebCrawlerParsingException(
                "Can not parse page links.",
                FestaLinkParser.class, new IndexOutOfBoundsException("no page link parsed."));
        }
        return links;
    }

    private Set<Integer> parseLinksFrom(final Document document) {
        try {
            return document.select(LIST_ROOT_SELECTOR)
                .stream()
                .filter(this::isNotExpired)
                .map(this::parsePageLink)
                .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new WebCrawlerParsingException("Can not parse page links.", FestaLinkParser.class, e);
        }
    }

    private boolean isNotExpired(final Element element) {
        return parseStartedAt(element).isAfter(LocalDateTime.now());
    }

    public LocalDateTime parseStartedAt(final Element element) {
        String timestamp = element.getElementsByTag("time")
            .get(0)
            .text();
        return parseDateTime(timestamp);
    }

    private LocalDateTime parseDateTime(final String timestamp) {
        try {
            return LocalDateTime.parse(timestamp, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalStateException("Cant not parse " + timestamp);
        }
    }

    private Integer parsePageLink(final Element element) {
        String link = element
            .getElementsByTag("a")
            .attr("href")
            .split("/")[2];

        try {
            return Integer.parseInt(link);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Cant not parse " + e);
        }
    }
}
