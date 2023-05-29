package me.lsh.javacrawler.config.driver;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DriverConfig {

    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

    private static final String WEB_DRIVER_PATH = "src/main/java/me/lsh/javacrawler/config/driver/chromedriver";

    @Bean
    @Scope("singleton")
    public WebDriver chromeDriver() {
        ChromeDriver driver = new ChromeDriver(chromeOptions());
//        driver
//            .manage()
//            .timeouts()
//            .pageLoadTimeout(Duration.of(30, ChronoUnit.SECONDS));
        return driver;
    }

    @Bean
    public ChromeOptions chromeOptions() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
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
        options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/605.1.15");
        return options;
    }
}
