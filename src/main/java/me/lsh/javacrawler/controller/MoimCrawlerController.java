package me.lsh.javacrawler.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.aspect.annotation.CrawlLogging;
import me.lsh.javacrawler.aspect.annotation.CrawlReport;
import me.lsh.javacrawler.service.crawler.MoimCrawlerService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Profile("!crawlerTest")
@RequiredArgsConstructor
public class MoimCrawlerController {

    private final MoimCrawlerService crawlerService;

    // 처음 실행 된 후 30초(120000/2[=1분]/2) 후 실행, 이후 이전에 실행된 task의 종료 시긴으로 부터 1분(300000/5) 후에 실행됨.
    @CrawlReport
    @CrawlLogging
    @Scheduled(fixedDelay = 45000000, initialDelay = 120000)
    public void crawlAll() {
        crawlerService.crawlAll();
    }
}