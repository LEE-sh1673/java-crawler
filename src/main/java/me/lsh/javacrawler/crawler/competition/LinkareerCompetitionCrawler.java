package me.lsh.javacrawler.crawler.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.crawler.EventCrawler;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.parser.event.competition.CompetitionParser;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LinkareerCompetitionCrawler extends EventCrawler
    implements CompetitionCrawler {

    private static final int DEFAULT_CRAWL_PAGES = 5;

    private static final String BASE_URL = "https://linkareer.com/list/contest"
        + "?filterBy_categoryIDs=35&filterType=CATEGORY"
        + "&orderBy_direction=DESC&orderBy_field=CREATED_AT";

    private final CompetitionParser parser;

    @Autowired
    public LinkareerCompetitionCrawler(final WebDriver driver,
        final CompetitionParser parser) {
        super(driver);
        this.parser = parser;
    }

    @Override
    public List<Integer> crawlLinks() {
        Set<Integer> links = new TreeSet<>(Collections.reverseOrder());
        for (int i = 1; i <= DEFAULT_CRAWL_PAGES; i++) {
            links.addAll(crawlLinksOf(i));
        }
        if (links.size() == 0) {
            throw new WebCrawlerParsingException("Can not parse page : " + BASE_URL,
                this.parser.getClass());
        }
        return new ArrayList<>(links);
    }

    private Set<Integer> crawlLinksOf(final int pageNumber) {
        return parser.parseLinks(fetchDocument(BASE_URL + "&page=" + pageNumber));
    }

    @Override
    public Competition crawl(final String url) {
        log.info("--------------------[단일 공모전 크롤링]--------------------");
        Competition competition = parser.parseCompetition(url, fetchDocument(url));
        log.info("--------------------[단일 공모전 크롤링 완료]----------------");
        return competition;
    }
}
