package me.lsh.javacrawler.service.recommend;

import me.lsh.javacrawler.repository.recommend.dto.RecommendCompetitionDtoProtection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class RecommendServiceTest {

    @Autowired
    private RecommendService recommendService;

    @Test
    void 빈도가_높은_시상규모_혜택_공모전_추천() {
        // given
        long memberId = 1L;
        Page<RecommendCompetitionDtoProtection> competitions
            = recommendService.recommendCompetitions(memberId, PageRequest.of(0, 5));

        System.out.println("competitions.getTotalElements() = " + competitions.getTotalElements());
        System.out.println("competitions.getTotalPages() = " + competitions.getTotalPages());

        for (RecommendCompetitionDtoProtection competition : competitions) {
            System.out.println("getTitle = " + competition.getTitle());
            System.out.println("getEventId = " + competition.getEventId());
            System.out.println("getAwardAmount = " + competition.getAwardAmount());
            System.out.println("getAwardScale = " + competition.getAwardScale());
        }
    }

    @Test
    void 사용자_관심_기술_공모전_추천() {
        // given
        long memberId = 1L;
        Page<RecommendCompetitionDtoProtection> competitions
            = recommendService.recommendCompetitionsBySkill(memberId, PageRequest.of(0, 5));

        System.out.println("competitions.getTotalElements() = " + competitions.getTotalElements());
        System.out.println("competitions.getTotalPages() = " + competitions.getTotalPages());

        for (RecommendCompetitionDtoProtection competition : competitions) {
            System.out.println("getTitle = " + competition.getTitle());
        }
    }

    @Test
    void 사용자_관심_기술_모임_추천() {
        // given
        long memberId = 1L;
        Page<RecommendListMoimDto> moims
            = recommendService.recommendMoimsBySkill(memberId, PageRequest.of(0, 5));

        System.out.println("competitions.getTotalElements() = " + moims.getTotalElements());
        System.out.println("competitions.getTotalPages() = " + moims.getTotalPages());

        for (RecommendListMoimDto moim : moims) {
            System.out.println("getPageId = " + moim.getId());
            System.out.println("getTitle = " + moim.getTitle());
            System.out.println("getDuration = " + moim.getDuration());
            System.out.println("getOrganizer = " + moim.getOrganizer());
        }
    }
}