package me.lsh.javacrawler.parser.event.competition;


import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.crawler.EventCrawlerReport;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import me.lsh.javacrawler.service.crawler.EventCrawlerService;

@Slf4j
@RequiredArgsConstructor
public abstract class EventCrawlerController {

    protected final EventCrawlerService crawlerService;

    protected final EventCrawlerReport crawlerReport;

    protected void crawlAll() {
        startCrawling();
        try {
            crawlerService.crawlAll();
        } catch (WebCrawlerParsingException e) {
            crawlerReport.report(e.getMessage());
        } finally {
            endCrawling();
        }
    }

    private void startCrawling() {
        log.info("크롤링 시작 [{}]", LocalDateTime.now());
        crawlerReport.report("크롤링 시작");
    }

    private void endCrawling() {
        crawlerReport.report("크롤링 종료");
        crawlerReport.save();
        log.info("크롤링 종료 [{}]", LocalDateTime.now());
    }
}
