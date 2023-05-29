package me.lsh.javacrawler.parser.moim.festa;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;
import me.lsh.javacrawler.config.driver.DriverConfig;
import me.lsh.javacrawler.parser.event.MoimLinkParser;
import me.lsh.javacrawler.parser.event.moim.festa.FestaLinkParser;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

class MoimLinkParserTest {

    private static final String BASE_URL = "https://festa.io/events";

    private static WebDriver driver;

    @BeforeAll
    static void tearUp() throws InterruptedException {
        DriverConfig driverConfig = new DriverConfig();
        driver = driverConfig.chromeDriver();
        driver.get(BASE_URL);
        Thread.sleep(1000);
        scroll();
    }

    static void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long initialHeight = (long) js.executeScript("return document.body.scrollHeight");
        long currentHeight = 0;

        while (currentHeight < initialHeight) {
            currentHeight = (long) js.executeScript("window.scrollTo(0, document.body.scrollHeight); return document.body.scrollHeight;");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void 전체_링크_파싱() {
        MoimLinkParser parser = new FestaLinkParser();
        Set<Integer> links = parser.parseLinks(Jsoup.parse(driver.getPageSource()));
        assertNotEquals(0, links.size());
    }
}