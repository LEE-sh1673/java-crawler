package me.lsh.javacrawler.parser.event.competition.linkareer;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.parser.event.CompetitionLinkParser;
import me.lsh.javacrawler.parser.event.competition.CompetitionContentParser;
import me.lsh.javacrawler.parser.event.competition.CompetitionParser;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkareerParser implements CompetitionParser {

    private final CompetitionLinkParser linkParser;

    private final CompetitionContentParser contentParser;

    @Override
    public Set<Integer> parseLinks(final Document document) {
        return linkParser.parseLinks(document);
    }

    @Override
    public Competition parseCompetition(final String url, final Document document) {
        try {
            return contentParser.parse(url, document);
        } catch (Exception e) {
            throw new WebCrawlerParsingException(e.getMessage(), url, contentParser.getClass(), e);
        }
    }
}
