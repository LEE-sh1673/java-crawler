package me.lsh.javacrawler.crawler.moim;

import me.lsh.javacrawler.crawler.Crawler;
import me.lsh.javacrawler.domain.event.moim.Moim;

public interface MoimCrawler extends Crawler {

    Moim crawl(final String url);
}
