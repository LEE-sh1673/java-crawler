package me.lsh.javacrawler.domain.crawler.competition;

import me.lsh.javacrawler.domain.crawler.Crawler;
import me.lsh.javacrawler.domain.event.competition.Competition;

public interface CompetitionCrawler extends Crawler {

    Competition crawl(final String url);
}
