package me.lsh.javacrawler.controller;

import me.lsh.javacrawler.crawler.MoimCrawlerReport;
import me.lsh.javacrawler.parser.event.competition.EventCrawlerController;
import me.lsh.javacrawler.service.crawler.MoimCrawlerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class MoimCrawlerController extends EventCrawlerController {

    public MoimCrawlerController(
        final MoimCrawlerService crawlerService,
        final MoimCrawlerReport crawlerReport) {
        super(crawlerService, crawlerReport);
    }

    // 처음 실행 된 후 30초(120000/2[=1분]/2) 후 실행, 이후 이전에 실행된 task의 종료 시긴으로 부터 1분(300000/5) 후에 실행됨.
    @Scheduled(fixedDelay = 300000, initialDelay = 120000)
    public void crawlAll() {
//        super.crawlAll();
    }
}
