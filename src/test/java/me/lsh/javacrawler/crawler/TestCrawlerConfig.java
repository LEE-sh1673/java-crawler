package me.lsh.javacrawler.crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestCrawlerConfig {

    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

    @Bean
    public WebDriver testDriver() {
        return new ChromeDriver(testOptions());
    }

    @Bean
    public ChromeOptions testOptions() {
        String webDriverPathWindows = "src/main/java/me/lsh/javacrawler/common/config/driver/window/chromedriver.exe";
        System.setProperty(WEB_DRIVER_ID, webDriverPathWindows);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("lang=ko");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--window-size=1512,1080");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments(
            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/605.1.15");
        return options;
    }
}
