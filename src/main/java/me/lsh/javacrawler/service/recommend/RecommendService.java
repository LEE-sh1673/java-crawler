package me.lsh.javacrawler.service.recommend;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.domain.skill.Skill;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentCompetitionAttribute;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentSkill;
import me.lsh.javacrawler.repository.recommend.dto.RecommendCompetitionDtoProtection;
import me.lsh.javacrawler.repository.recommend.RecommendCompetitionRepository;
import me.lsh.javacrawler.repository.recommend.RecommendMoimRepository;
import me.lsh.javacrawler.repository.recommend.dto.RecommendSkillCompetitionDtoProtection;
import me.lsh.javacrawler.service.BookmarkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendService {

    private final RecommendCompetitionRepository competitionRepository;

    private final RecommendMoimRepository moimRepository;

    private final BookmarkService bookmarkService;

    public Page<RecommendCompetitionDtoProtection> recommendCompetitions(final Long memberId,
        final Pageable pageable) {

        List<RecommendCompetitionDtoProtection> competitions
            = competitionRepository.findMostFrequentCompetitionsByAwardsAndBenefits(memberId);
        int totalCount = competitions.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), totalCount);
        return new PageImpl<>(competitions.subList(start, end), pageable, totalCount);
    }

    public MostFrequentCompetitionAttribute findMostFrequentCompetitionAttribute(
        final Long memberId) {
        return competitionRepository.findMostFrequentCompetitionAttribute(memberId);
    }

    public Page<RecommendCompetitionDtoProtection> recommendCompetitionsBySkill(
        final long memberId, final Pageable pageable) {
        List<RecommendCompetitionDtoProtection> competitions
            = competitionRepository.findCompetitionsByFrequentSkills(memberId).stream()
            .filter(
                (competition) -> !bookmarkService.isBookmarked(memberId, competition.getEventId()))
            .collect(Collectors.toList());
        int totalCount = competitions.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), totalCount);
        return new PageImpl<>(competitions.subList(start, end), pageable, totalCount);
    }

    public Page<RecommendListMoimDto> recommendMoimsBySkill(
        final long memberId, final Pageable pageable) {
        List<RecommendListMoimDto> moims
            = moimRepository.findMoimsByFrequentSkills(memberId).stream()
            .filter(
                (moim) -> !bookmarkService.isBookmarked(memberId, moim.getEventId()))
            .map(RecommendListMoimDto::new)
            .collect(Collectors.toList());
        return new PageImpl<>(moims, pageable, moims.size());
    }

    public List<Skill> getMostFrequentMoimSkillsInBookmark(final long memberId) {
        return moimRepository.findMostFrequentSkillInBookmark(memberId)
            .stream()
            .map(MostFrequentSkill::getSkill)
            .collect(Collectors.toList());
    }

    public List<Skill> getMostFrequentCompetitionSkillsInBookmark(final long memberId) {
        return competitionRepository.findMostFrequentSkillInBookmark(memberId)
            .stream()
            .map(MostFrequentSkill::getSkill)
            .collect(Collectors.toList());
    }
}
