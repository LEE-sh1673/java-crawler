package me.lsh.javacrawler.parser.event.competition.linkareer;

import java.util.Set;
import java.util.stream.Collectors;
import me.lsh.javacrawler.parser.event.CompetitionLinkParser;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class LinkareerLinkParser implements CompetitionLinkParser {

    private static final String ROOT_SELECTOR = ".MuiContainer-root:gt(1)";

    @Override
    public Set<Integer> parseLinks(final Document document) throws WebCrawlerParsingException {
        return parseLinksFrom(document);
    }

    private static Set<Integer> parseLinksFrom(Document document) {
        try {
            return document.select(ROOT_SELECTOR)
                .select("div:nth-child(2) div > div")
                .stream()
                .map(el -> el.getElementsByTag("a").attr("href"))
                .filter(el -> !el.isEmpty() && !el.startsWith("ad"))
                .map(el -> Integer.parseInt(el.substring(el.lastIndexOf('/') + 1)))
                .collect(Collectors.toSet());
        } catch (Exception e) {
           throw new WebCrawlerParsingException("Can not parse page links.", LinkareerLinkParser.class, e);
        }
    }
}
