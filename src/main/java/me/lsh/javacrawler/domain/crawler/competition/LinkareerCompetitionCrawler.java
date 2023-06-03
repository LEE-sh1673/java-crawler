package me.lsh.javacrawler.domain.crawler.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.crawler.EventCrawlDriver;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.parser.event.competition.CompetitionParser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkareerCompetitionCrawler implements CompetitionCrawler {

    private static final int DEFAULT_CRAWL_PAGES = 5;

    private static final String BASE_URL = "https://linkareer.com/list/contest"
        + "?filterBy_categoryIDs=35&filterType=CATEGORY"
        + "&orderBy_direction=DESC&orderBy_field=CREATED_AT";

    private final CompetitionParser parser;

    private final EventCrawlDriver driver;

    @Override
    public List<Integer> crawlLinks() {
        Set<Integer> links = new TreeSet<>(Collections.reverseOrder());
        for (int i = 1; i <= DEFAULT_CRAWL_PAGES; i++) {
            links.addAll(crawlLinksOf(i));
        }
        return new ArrayList<>(links);
    }

    private Set<Integer> crawlLinksOf(final int pageNumber) {
        return parser.parseLinks(driver.fetch(BASE_URL + "&page=" + pageNumber));
    }

    @Override
    public Competition crawl(final String url) {
        log.info("--------------------[단일 공모전 크롤링]--------------------");
        Competition competition = parser.parseCompetition(url, driver.fetch(url));
        log.info("--------------------[단일 공모전 크롤링 완료]----------------");
        return competition;
    }
}
