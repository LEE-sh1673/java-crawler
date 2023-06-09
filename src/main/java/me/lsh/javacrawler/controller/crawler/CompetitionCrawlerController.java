package me.lsh.javacrawler.controller.crawler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.aspect.annotation.CrawlLogging;
import me.lsh.javacrawler.aspect.annotation.CrawlReport;
import me.lsh.javacrawler.service.crawler.competition.CompetitionCrawlerService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Profile("!test")
@RequiredArgsConstructor
public class CompetitionCrawlerController {

    private final CompetitionCrawlerService crawlerService;

    // 처음 실행 된 후 5초후 실행, 이후 이전에 실행된 task의 종료 시긴으로 부터 750분(45000000) 후에 실행됨.
    @CrawlReport
    @CrawlLogging
    @Scheduled(fixedDelay = 45000000, initialDelay = 5000)
    public void crawlAll() {
        crawlerService.crawlAll();
    }
}
