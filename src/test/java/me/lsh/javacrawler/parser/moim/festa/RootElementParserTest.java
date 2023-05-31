package me.lsh.javacrawler.parser.moim.festa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import me.lsh.javacrawler.config.driver.DriverConfig;
import me.lsh.javacrawler.crawler.TestCrawlerConfig;
import me.lsh.javacrawler.parser.event.moim.festa.RootElementParser;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({TestCrawlerConfig.class})
class RootElementParserTest {

    private static final String BASE_URL = "https://festa.io/events/3021";

    private static WebDriver driver;

    private RootElementParser parser;


    @BeforeAll
    static void tearUp() throws InterruptedException {
        TestCrawlerConfig driverConfig = new TestCrawlerConfig();
        driver = driverConfig.testDriver();
        driver.get(BASE_URL);
        Thread.sleep(1000);
    }

    @BeforeEach
    void setUp() {
        parser = new RootElementParser(Jsoup.parse(driver.getPageSource()));
    }

    @Test
    void 썸네일_파싱() {
        String thumbnail = parser.parseThumbnail();
        assertEquals("https://cf.festa.io/img/2022-12-31/9db969a2-14f4-444e-96dd-87db64d46867.PNG", thumbnail);
    }

    @Test
    void 제목_파싱() {
        String title = parser.parseTitle();
        assertEquals("[무료상담] 직장인 고민상담 코칭", title);
    }

    @Test
    void 장소_파싱() {
        String location = parser.parseLocation();
        assertEquals("서울 강남구", location);
    }

    @Test
    void 기간_파싱() {
        String duration = parser.parseDuration();
        assertEquals("2023년 12월 31일 (일) 오후 04:00 - 오후 05:00", duration);
    }

    @Test
    void 주최자_파싱() {
        String organizer = parser.parseOrganizer();
        assertEquals("직장인상담코칭", organizer);
    }

    @Test
    void 유료_여부_파싱() {
        boolean feeRequired = parser.parseFeeRequired();
        assertFalse(feeRequired);
    }
}