package me.lsh.javacrawler.aspect;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class CrawlLoggingAspect {

    @Before("@annotation(me.lsh.javacrawler.aspect.annotation.CrawlLogging)")
    public void beforeCrawl() {
        log.info("크롤링 시작 [{}]", LocalDateTime.now());
    }

    @After("@annotation(me.lsh.javacrawler.aspect.annotation.CrawlLogging)")
    public void afterCrawlAll() {
        log.info("크롤링 종료 [{}]", LocalDateTime.now());
    }
}
