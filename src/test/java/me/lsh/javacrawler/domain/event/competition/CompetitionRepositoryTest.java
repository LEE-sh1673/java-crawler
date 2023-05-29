package me.lsh.javacrawler.domain.event.competition;

import static me.lsh.javacrawler.domain.event.ApplicantType.NO_LIMIT;
import static me.lsh.javacrawler.domain.event.ApplicantType.OFFICE_WORKER;
import static me.lsh.javacrawler.domain.event.ApplicantType.TEENAGER;
import static me.lsh.javacrawler.domain.event.ApplicantType.UNIV_STUDENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import me.lsh.javacrawler.controller.dto.CompetitionSearch;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.competition.Competition.CompetitionBuilder;
import me.lsh.javacrawler.repository.event.DateOption;
import me.lsh.javacrawler.repository.event.competition.CompetitionRepository;
import me.lsh.javacrawler.service.dto.CompetitionListResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@DataJpaTest
@Import({TestJPAConfig.class})
class CompetitionRepositoryTest {

    @Autowired
    private CompetitionRepository competitionRepository;

    public CompetitionBuilder competitionBuilder() {
        return Competition.builder()
            .title(UUID.randomUUID().toString())
            .startedAt(LocalDateTime.now().minusDays(1))
            .expiredAt(LocalDateTime.now().plusDays(7))
            .awardAmount("100");
    }

    @Test
    void 행사_저장() {
        Competition competition = competitionBuilder()
            .title("[30회 서울디저트페어] 초코&바나나 케이크 공모전")
            .pageId(11253)
            .organizer("서울디저트페어")
            .applicants(Set.of(NO_LIMIT))
            .build();

        Competition savedCompetition = competitionRepository.save(competition);
        assertEquals("[30회 서울디저트페어] 초코&바나나 케이크 공모전", savedCompetition.getTitle());
        assertEquals("서울디저트페어", savedCompetition.getOrganizer());

        Assertions.assertThat(savedCompetition.getApplicants())
            .containsExactlyInAnyOrder(NO_LIMIT);
    }

    @Test
    void 행사_행사번호_조회() {
        // given
        int pageId = 100;
        Competition competition = competitionBuilder()
            .pageId(pageId)
            .provider(EventProvider.LINKAREER)
            .build();

        // when
        competitionRepository.save(competition);

        // then
        assertTrue(competitionRepository.existsByPageIdAndProvider(pageId, EventProvider.LINKAREER));
    }

    @Test
    void 행사_참여대상_제한없음() {
        // given
        Competition noLimitComp1 = competitionBuilder()
            .applicants(Set.of(NO_LIMIT))
            .awardAmount("100")
            .build();

        Competition noLimitComp2 = competitionBuilder()
            .applicants(Set.of(NO_LIMIT))
            .awardAmount("100")
            .build();

        Competition univOfficeComp = competitionBuilder()
            .applicants(Set.of(UNIV_STUDENT, OFFICE_WORKER))
            .awardAmount("100")
            .build();

        competitionRepository.save(noLimitComp1);
        competitionRepository.save(noLimitComp2);
        competitionRepository.save(univOfficeComp);

        // when
        CompetitionSearch searchOption = new CompetitionSearch();
        searchOption.setApplicantTypes(List.of(NO_LIMIT));
        Page<CompetitionListResponseDto> competitions
            = competitionRepository.findAllByCriteria(searchOption, PageRequest.of(1, 12));

        // then
        assertEquals(2, competitions.getTotalElements());
    }

    @Test
    void 행사_참여대상_청소년_직장인() {
        // given
        Competition univOnlyComp1 = competitionBuilder()
            .applicants(Set.of(UNIV_STUDENT))
            .build();

        Competition univOnlyComp2 = competitionBuilder()
            .applicants(Set.of(UNIV_STUDENT))
            .build();

        Competition teenOnlyComp = competitionBuilder()
            .title("teen comp")
            .applicants(Set.of(TEENAGER))
            .build();

        Competition univOfficeWorkerComp = competitionBuilder()
            .title("univ & office comp")
            .applicants(Set.of(UNIV_STUDENT, OFFICE_WORKER))
            .build();

        competitionRepository.save(univOnlyComp1);
        competitionRepository.save(univOnlyComp2);
        competitionRepository.save(teenOnlyComp);
        competitionRepository.save(univOfficeWorkerComp);

        // when
        CompetitionSearch searchOption = new CompetitionSearch();
        searchOption.setApplicantTypes(List.of(TEENAGER, OFFICE_WORKER));
        Page<CompetitionListResponseDto> competitions
            = competitionRepository.findAllByCriteria(searchOption, PageRequest.of(1, 12));

        // then
        assertEquals(2, competitions.getTotalElements());
    }

    @Test
    void 행사_지금() {
        // given
        Competition nowComp = competitionBuilder()
            .startedAt(LocalDateTime.of(LocalDate.now(), LocalTime.MIN))
            .expiredAt(LocalDateTime.of(LocalDate.now(), LocalTime.MAX))
            .build();

        // when
        competitionRepository.save(nowComp);

        CompetitionSearch searchOption = new CompetitionSearch();
        searchOption.setDateOption(DateOption.TODAY);
        Page<CompetitionListResponseDto> competitions
            = competitionRepository.findAllByCriteria(searchOption, PageRequest.of(1, 12));

        // then
        assertEquals(1, competitions.getTotalElements());
    }

    @Test
    void 행사_이번달() {
        // given
        Competition nowComp = competitionBuilder()
            .startedAt(LocalDateTime.of(LocalDate.now().minusMonths(2), LocalTime.MIN))
            .expiredAt(LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.MAX))
            .build();

        Competition monthComp = competitionBuilder()
            .startedAt(LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.MIN))
            .expiredAt(LocalDateTime.of(LocalDate.now(), LocalTime.MAX))
            .build();

        competitionRepository.save(nowComp);
        competitionRepository.save(monthComp);

        // when
        CompetitionSearch searchOption = new CompetitionSearch();
        searchOption.setDateOption(DateOption.THIS_MONTH);
        Page<CompetitionListResponseDto> competitions
            = competitionRepository.findAllByCriteria(searchOption, PageRequest.of(1, 12));

        // then
        assertEquals(1, competitions.getTotalElements());
    }
}