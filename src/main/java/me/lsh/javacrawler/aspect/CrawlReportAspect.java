package me.lsh.javacrawler.aspect;


import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.controller.CompetitionCrawlerController;
import me.lsh.javacrawler.crawler.CompetitionCrawlerReport;
import me.lsh.javacrawler.crawler.EventCrawlerReport;
import me.lsh.javacrawler.crawler.MoimCrawlerReport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CrawlReportAspect {

    private final CompetitionCrawlerReport competitionReport;

    private final MoimCrawlerReport moimReport;

    @Around("@annotation(me.lsh.javacrawler.aspect.annotation.CrawlReport)")
    public Object captureAndProcess(ProceedingJoinPoint joinPoint) {
        EventCrawlerReport report = getReport(joinPoint);
        Object object = null;
        report.report("크롤링 시작");

        try {
            object = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            report.report(e.getMessage());
        } finally {
            report.report("크롤링 종료");
            report.save();
        }
        return object;
    }

    private EventCrawlerReport getReport(final JoinPoint joinPoint) {
        Class<?> crawlerClass = joinPoint.getTarget().getClass();

        if (crawlerClass == CompetitionCrawlerController.class) {
            return competitionReport;
        }
        return moimReport;
    }
}
