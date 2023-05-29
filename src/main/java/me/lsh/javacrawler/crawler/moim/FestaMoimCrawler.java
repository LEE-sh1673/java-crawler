package me.lsh.javacrawler.crawler.moim;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.crawler.EventCrawler;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import me.lsh.javacrawler.parser.event.moim.MoimParser;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FestaMoimCrawler extends EventCrawler
    implements MoimCrawler {

    private static final String BASE_URL = "https://festa.io/events/";

    private final MoimParser parser;

    @Autowired
    public FestaMoimCrawler(final WebDriver driver, final MoimParser parser) {
        super(driver);
        this.parser = parser;
    }

    @Override
    public List<Integer> crawlLinks() {
        List<Integer> links = parser.parseLinks(fetchDocument(BASE_URL))
            .stream()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());

        if (links.size() == 0) {
            throw new WebCrawlerParsingException("Can not parse page : " + BASE_URL,
                this.parser.getClass());
        }
        return links;
    }

    @Override
    public Moim crawl(final String url) {
        log.info("--------------------[단일 모임 크롤링]--------------------");
        Moim moim = parser.parseMoim(url, fetchDocument(url));
        log.info("--------------------[단일 모임 크롤링 완료]----------------");
        return moim;
    }
}
