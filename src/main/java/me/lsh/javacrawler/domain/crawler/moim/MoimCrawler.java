package me.lsh.javacrawler.domain.crawler.moim;

import me.lsh.javacrawler.domain.crawler.Crawler;
import me.lsh.javacrawler.domain.event.moim.Moim;

public interface MoimCrawler extends Crawler {

    Moim crawl(final String url);
}
