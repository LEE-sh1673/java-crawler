package me.lsh.javacrawler.domain.parser.event.moim;

import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.parser.event.EventParser;
import org.jsoup.nodes.Document;

public interface MoimParser extends EventParser {

    Moim parseMoim(final String url, final Document document);
}
