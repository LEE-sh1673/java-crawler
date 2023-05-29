package me.lsh.javacrawler.parser.event.moim;

import me.lsh.javacrawler.domain.event.moim.Moim;
import org.jsoup.nodes.Document;

public interface MoimContentParser {

    Moim parse(final String url, final Document document);
}
