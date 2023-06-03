package me.lsh.javacrawler.domain.crawler;

import java.util.List;
import me.lsh.javacrawler.domain.event.Event;

public interface Crawler {

    Event crawl(final String url);

    List<Integer> crawlLinks();
}
