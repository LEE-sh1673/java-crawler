package me.lsh.javacrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class JavaCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCrawlerApplication.class, args);
    }
}
