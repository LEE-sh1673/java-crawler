package me.lsh.javacrawler.domain.parser.event.competition;

import me.lsh.javacrawler.domain.parser.event.EventParser;
import me.lsh.javacrawler.domain.event.competition.Competition;
import org.jsoup.nodes.Document;

public interface CompetitionParser extends EventParser {

    Competition parseCompetition(final String url, final Document document);
}
