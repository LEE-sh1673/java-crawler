package me.lsh.javacrawler.crawler;

import java.util.List;
import java.util.Set;
import me.lsh.javacrawler.domain.event.Event;

public interface Crawler {

    Event crawl(final String url);

    List<Integer> crawlLinks();
}
