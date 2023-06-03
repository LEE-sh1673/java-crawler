package me.lsh.javacrawler.controller.recommend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.service.recommend.statistics.CompetitionStatisticService;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@Profile("!test")
@RequiredArgsConstructor
public class EventStatisticController {

    private final CompetitionStatisticService statisticService;

    // 일요일마다 오후 8시에 실행 (0 0 20 * * SUN)
    // test: 매일 5분마다 실행 (0 0/5 * * * ?)
    @Scheduled(cron = "0 0 20 * * SUN")
    public void collectRecommendStatistics() {
        log.info("추천 데이터 통계 시작");
        statisticService.collect();
    }
}
