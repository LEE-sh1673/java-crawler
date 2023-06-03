package me.lsh.javacrawler.domain.crawler;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class CompetitionCrawlerReport extends EventCrawlerReport {

    private static final DateTimeFormatter REPORT_POSTFIX_TIMESTAMP_FORMAT
        = DateTimeFormatter.ofPattern("yyyy-MM-dd__HH-mm-ss-SSS");

    private final String REPORT_TITLE_PREFIX = "COMPETITION_CRAWLING_REPORT";

    @Override
    public void save() {
        String fileName = String.format(
            "%s__%s",
            "competition" + File.separator + REPORT_TITLE_PREFIX,
            LocalDateTime.now().format(REPORT_POSTFIX_TIMESTAMP_FORMAT));
        super.saveToFile(fileName);
    }
}
