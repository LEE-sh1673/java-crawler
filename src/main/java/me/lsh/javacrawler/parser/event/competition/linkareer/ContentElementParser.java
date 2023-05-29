package me.lsh.javacrawler.parser.event.competition.linkareer;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ContentElementParser {

    private static final String CONTENT_SELECTOR = "div.MuiTabs-root ~ div > div > div > div:nth-child(2)";

    private final Elements elements;

    public ContentElementParser(final Document document) {
        this.elements = document.select(CONTENT_SELECTOR);
    }

    public String parseContent() {
        return elements.get(0).text();
    }

    public String parseContentHTML() {
        return elements.get(0).outerHtml();
    }
}
