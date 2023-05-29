package me.lsh.javacrawler.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class EventCrawlerReport {

    private static final DateTimeFormatter MESSAGE_TIMESTAMP_FORMAT
        = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private static final String REPORT_PATH = "src/main/resources/reports/";

    private final List<String> messages;

    protected EventCrawlerReport() {
        this.messages = new ArrayList<>();
    }

    public void report(final String message) {
        messages.add(formatMessage(message));
    }

    private String formatMessage(final String message) {
        String timestamp = LocalDateTime.now().format(MESSAGE_TIMESTAMP_FORMAT);
        return timestamp + " " + message;
    }

    protected void saveToFile(final String fileName) {
        String name = REPORT_PATH + fileName + ".txt";

        try (FileWriter writer = new FileWriter(name)) {
            for (String message : messages) {
                writer.write(message + System.lineSeparator());
            }
            log.info("레포트를 저장하였습니다.  : {}", name);
        } catch (IOException e) {
            log.error("레포트 저장에 실패하였습니다:  {}", e.getMessage());
        } finally {
            messages.clear();
        }
    }

    abstract public void save();
}
