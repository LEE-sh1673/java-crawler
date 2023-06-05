package me.lsh.javacrawler.common.config.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {

    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

    @Value("${webdriver.chrome.driver.path}")
    private String webDriverPath;


    @Bean
    public WebDriver chromeDriver() {
        return new ChromeDriver(chromeOptions());
    }


    @Bean
    public ChromeOptions chromeOptions() {
        System.setProperty(WEB_DRIVER_ID, webDriverPath);

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
