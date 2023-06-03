package me.lsh.javacrawler.domain.parser.event.competition.linkareer;

import java.util.Set;
import java.util.stream.Collectors;
import me.lsh.javacrawler.domain.parser.event.competition.CompetitionLinkParser;
import me.lsh.javacrawler.domain.parser.exception.WebCrawlerParseException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Selector.SelectorParseException;
import org.springframework.stereotype.Component;

@Component
public class LinkareerLinkParser implements CompetitionLinkParser {

    private static final String ROOT_SELECTOR = ".MuiContainer-root:gt(1)";

    @Override
    public Set<Integer> parseLinks(final Document document) {
        try {
            return parseLinksFrom(document);
        } catch (SelectorParseException e) {
            throw new WebCrawlerParseException("Can not parse page links.", LinkareerLinkParser.class);
        }
    }

    private static Set<Integer> parseLinksFrom(final Document document) {
        return document.select(ROOT_SELECTOR)
            .select("div:nth-child(2) div > div")
            .stream()
            .map(el -> el.getElementsByTag("a").attr("href"))
            .filter(el -> !el.isEmpty() && !el.startsWith("ad"))
            .map(el -> Integer.parseInt(el.substring(el.lastIndexOf('/') + 1)))
            .collect(Collectors.toSet());
    }
}
