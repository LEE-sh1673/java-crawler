package me.lsh.javacrawler.domain.parser.event;

import java.util.Set;
import org.jsoup.nodes.Document;

public interface EventParser {

    Set<Integer> parseLinks(final Document document);
}
