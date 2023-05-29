package me.lsh.javacrawler.crawler;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class CompetitionCrawlerReport extends EventCrawlerReport {

    private final String REPORT_TITLE_PREFIX = "COMPETITION_CRAWLING_REPORT";

    @Override
    public void save() {
        super.saveToFile(String.format("%s__%s", REPORT_TITLE_PREFIX, LocalDateTime.now()));
    }
}
