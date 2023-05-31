package me.lsh.javacrawler.crawler.moim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import me.lsh.javacrawler.controller.CompetitionCrawlerController;
import me.lsh.javacrawler.controller.MoimCrawlerController;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.event.moim.MoimType;
import me.lsh.javacrawler.domain.skill.Skill;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class MoimCrawlerTest {

    @Autowired
    private MoimCrawler crawler;

    private final Moim expected = Moim
        .builder()
        .pageId(3021)
        .originalLink("https://festa.io/events/3021")
        .title("[무료상담] 직장인 고민상담 코칭")
        .organizer("직장인상담코칭")
        .applicants(Set.of(ApplicantType.NO_LIMIT))
        .skills(Set.of(Skill.NO_SKILL))
        .thumbnail("https://cf.festa.io/img/2022-12-31/9db969a2-14f4-444e-96dd-87db64d46867.PNG")
        .bookmarkCount(0)
        .viewCount(0)
        .feeRequired(false)
        .moimType(MoimType.OFFLINE)
        .startedAt(LocalDateTime.of(LocalDate.of(2023, 12, 31), LocalTime.MIN))
        .expiredAt(LocalDateTime.of(LocalDate.of(2023, 12, 31), LocalTime.MAX))
        .build();

    @Test
    void 이벤트_크롤링_테스트()  {
        Moim moim = crawler.crawl("https://festa.io/events/3021");
        assertEquals(expected.getTitle(), moim.getTitle());
        assertEquals(expected.getOrganizer(), moim.getOrganizer());
        assertEquals(expected.getStatus(), moim.getStatus());

        Assertions.assertThat(moim.getApplicants())
            .containsAll(expected.getApplicants());
    }
}