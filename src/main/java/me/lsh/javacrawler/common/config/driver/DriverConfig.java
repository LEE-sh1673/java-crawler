package me.lsh.javacrawler.common.config.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DriverConfig {

    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

    @Value("${webdriver.chrome.driver.windows}")
    private String webDriverPathWindows;

    @Value("${webdriver.chrome.driver.macos}")
    private String webDriverPathMacOS;

    @Value("${webdriver.chrome.driver.linux}")
    private String webDriverPathLinux;


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
        String os = System.getProperty("os.name").toLowerCase();
        System.setProperty(WEB_DRIVER_ID, determineWebDriverPath(os));

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

    private String determineWebDriverPath(String os) {
        if (os.contains("win")) {
            return webDriverPathWindows;
        } else if (os.contains("mac")) {
            return webDriverPathMacOS;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return webDriverPathLinux;
        } else {
            throw new IllegalStateException("Unsupported operating system: " + os);
        }
    }
}
