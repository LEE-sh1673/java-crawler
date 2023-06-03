package me.lsh.javacrawler.domain.crawler.moim;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.crawler.EventCrawlDriver;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.parser.event.moim.MoimParser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FestaMoimCrawler implements MoimCrawler {

    private static final String BASE_URL = "https://festa.io/events/";

    private final MoimParser parser;

    private final EventCrawlDriver driver;


    @Override
    public List<Integer> crawlLinks() {
        return parser.parseLinks(driver.fetch(BASE_URL))
            .stream()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());
    }

    @Override
    public Moim crawl(final String url) {
        log.info("--------------------[단일 모임 크롤링]--------------------");
        Moim moim = parser.parseMoim(url, driver.fetch(url));
        log.info("--------------------[단일 모임 크롤링 완료]----------------");
        return moim;
    }
}
