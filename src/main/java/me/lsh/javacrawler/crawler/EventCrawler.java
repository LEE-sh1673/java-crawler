package me.lsh.javacrawler.crawler;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.domain.event.Event;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class EventCrawler implements Crawler {

    private static final String PAGE_HEIGHT_JS_SCRIPT
        = "return document.body.scrollHeight";

    private static final String PAGE_SCROLL_JS_SCRIPT
        = "window.scrollTo(0, document.body.scrollHeight); return document.body.scrollHeight;";

    private final WebDriver driver;

    protected Document fetchDocument(final String url) {
        goToPage(url);
        return Jsoup.parse(driver.getPageSource());
    }

    private void goToPage(final String url) {
        try {
            driver.get(url);
            Thread.sleep(1000);
            scroll();
        } catch (Exception e) {
            throw new IllegalStateException("Can not access page : " + url);
        }
    }

    protected void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long initialHeight = (long) js.executeScript(PAGE_HEIGHT_JS_SCRIPT);
        long currentHeight = 0;

        while (currentHeight < initialHeight) {
            currentHeight = (long) js.executeScript(PAGE_SCROLL_JS_SCRIPT);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public abstract Event crawl(final String url);

    @Override
    public abstract List<Integer> crawlLinks();
}
