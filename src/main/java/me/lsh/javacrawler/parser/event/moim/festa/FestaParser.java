package me.lsh.javacrawler.parser.event.moim.festa;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.parser.event.MoimLinkParser;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import me.lsh.javacrawler.parser.event.moim.MoimContentParser;
import me.lsh.javacrawler.parser.event.moim.MoimParser;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FestaParser implements MoimParser {

    private final MoimLinkParser linkParser;

    private final MoimContentParser contentParser;

    @Override
    public Set<Integer> parseLinks(final Document document) {
        return  linkParser.parseLinks(document);
    }

    @Override
    public Moim parseMoim(final String url, final Document document) {
        try {
            return contentParser.parse(url, document);
        } catch (Exception e) {
            throw new WebCrawlerParsingException(e.getMessage(), url, contentParser.getClass(), e);
        }
    }
}
