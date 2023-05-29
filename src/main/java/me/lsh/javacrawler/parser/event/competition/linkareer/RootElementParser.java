package me.lsh.javacrawler.parser.event.competition.linkareer;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Slf4j
public class RootElementParser {

    private static final String ROOT_SELECTOR = ".MuiContainer-root:gt(1)";

    private final Elements elements;

    public RootElementParser(final Document document) {
        this.elements = document.select(ROOT_SELECTOR);
    }

    public String parseTitle() {
        String title = elements.select("h1").text();
        log.info("제목 파싱 = {}", title);
        return title;
    }

    public String parseThumbnailURL() {
        String thumbnail = elements.select("img").attr("src");
        log.info("썸네일 파싱 = {}", thumbnail);
        return thumbnail;
    }

    public String parseOrganizer() {
        String organizer = elements.select("h2").text();
        log.info("주최자 파싱 = {}", organizer);
        return organizer;
    }
}
