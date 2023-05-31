package me.lsh.javacrawler.crawler;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class EventCrawlerReport {

    private static final DateTimeFormatter MESSAGE_TIMESTAMP_FORMAT
        = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private static final String REPORT_PATH = "static/reports/";

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
        String filePath = getFilePath(fileName);
        writeToFile(getFilePath(fileName));
        log.info("레포트를 저장하였습니다.  : {}", filePath);
        messages.clear();
    }

    private String getFilePath(final String fileName) {
        return REPORT_PATH
            + fileName
            + ".txt";
    }

    private void writeToFile(final String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(getFileContent(messages));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFileContent(List<String> messages) {
        return messages.stream()
            .map(message -> message + '\n')
            .collect(Collectors.joining());
    }

    abstract public void save();
}
