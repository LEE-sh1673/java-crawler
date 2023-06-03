package me.lsh.javacrawler.domain.parser.event.competition.linkareer;

import me.lsh.javacrawler.domain.parser.exception.WebCrawlerParseException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentElementParser {

    private static final String CONTENT_SELECTOR = "div.MuiTabs-root ~ div > div > div > div:nth-child(2)";

    private final Elements elements;

    public ContentElementParser(final Document document) {
        this.elements = document.select(CONTENT_SELECTOR);
    }

    public String parseContent() {
        return getContentElement().text();
    }

    public String parseContentHTML() {
        return getContentElement().outerHtml();
    }

    private Element getContentElement() {
        try {
            return elements.get(0);
        } catch (Exception e) {
            throw new WebCrawlerParseException("Can not parse page content.",
                this.getClass(), e);
        }
    }
}
