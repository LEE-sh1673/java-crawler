package me.lsh.javacrawler.crawler.competition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import me.lsh.javacrawler.crawler.competition.CompetitionCrawler;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.skill.Skill;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompetitionCrawlerTest {

    @Autowired
    private CompetitionCrawler crawler;

    private final Competition expected = Competition
        .builder()
        .pageId(131927)
        .originalLink("https://linkareer.com/activity/131927")
        .title("도심에서 열리는 해커톤, SBA x goorm 새싹톤 (~5/9)")
        .organizer("(주)구름")
        .applicants(Set.of(ApplicantType.UNIV_STUDENT, ApplicantType.OFFICE_WORKER))
        .awardBenefits(Set.of(AwardBenefit.DEFAULT))
        .awardAmount("1500만원")
        .skills(Set.of(Skill.AI))
        .thumbnail("https://res.cloudinary.com/linkareer/image/fetch/f_auto,q_50/https://api.linkareer.com/attachments/216524")
        .bookmarkCount(0)
        .viewCount(0)
        .startedAt(LocalDateTime.of(LocalDate.of(2023, 4, 26), LocalTime.MIN))
        .expiredAt(LocalDateTime.of(LocalDate.of(2023, 5, 9), LocalTime.MAX))
        .build();

    @Test
    void 이벤트_크롤링_테스트() {
        Competition competition = crawler.crawl("https://linkareer.com/activity/131927");
        assertEquals(expected.getTitle(), competition.getTitle());
        assertEquals(expected.getOrganizer(), competition.getOrganizer());
        assertEquals(expected.getStatus(), competition.getStatus());

        Assertions.assertThat(competition.getApplicants())
            .containsAll(expected.getApplicants());
    }
}