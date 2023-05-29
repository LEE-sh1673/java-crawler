package me.lsh.javacrawler.parser.event;

import java.util.Set;
import org.jsoup.nodes.Document;

public interface EventLinkParser {

    Set<Integer> parseLinks(final Document document);
}
