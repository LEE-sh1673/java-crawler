package me.lsh.javacrawler.domain.parser.event.competition;

import me.lsh.javacrawler.domain.event.competition.Competition;
import org.jsoup.nodes.Document;

public interface CompetitionContentParser {

    Competition parse(final String url, final Document document);
}
