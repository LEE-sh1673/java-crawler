package me.lsh.javacrawler.controller;

import me.lsh.javacrawler.crawler.CompetitionCrawlerReport;
import me.lsh.javacrawler.parser.event.competition.EventCrawlerController;
import me.lsh.javacrawler.service.crawler.CompetitionCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class CompetitionCrawlerController extends EventCrawlerController {

    @Autowired
    public CompetitionCrawlerController(
        final CompetitionCrawlerService crawlerService,
        final CompetitionCrawlerReport crawlerReport) {
        super(crawlerService, crawlerReport);
    }

    // 처음 실행 된 후 5초후 실행, 이후 이전에 실행된 task의 종료 시긴으로 부터 5분(300000) 후에 실행됨.
    @Override
    @Scheduled(fixedDelay = 300000, initialDelay = 5000)
    public void crawlAll() {
//        super.crawlAll();
    }
}
