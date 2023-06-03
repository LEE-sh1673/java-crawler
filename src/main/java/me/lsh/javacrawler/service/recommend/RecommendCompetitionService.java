package me.lsh.javacrawler.service.recommend;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.controller.recommend.dto.RecommendCompetitionsListResponseDto;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.recommend.FrequentCompetitionAttribute;
import me.lsh.javacrawler.domain.recommend.FrequentSkill;
import me.lsh.javacrawler.repository.event.competition.CompetitionRepository;
import me.lsh.javacrawler.repository.recommend.RecommendCompetitionRepository;
import me.lsh.javacrawler.repository.recommend.dto.FrequentCompetitionAttributeDto;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentSkillDto;
import me.lsh.javacrawler.service.bookmark.BookmarkService;
import me.lsh.javacrawler.service.dto.CompetitionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecommendCompetitionService {

    private final RecommendCompetitionRepository recommendRepository;

    private final CompetitionRepository competitionRepository;

    private final BookmarkService bookmarkService;

    public Page<RecommendCompetitionsListResponseDto> findAllByMemberId(final long memberId, final Pageable pageable) {
        return recommendRepository.findAllByMemberId(memberId, pageable)
            .map(RecommendCompetitionsListResponseDto::new);
    }

    public LocalDateTime findCreatedAt(final Long memberId, final Long recommendId) {
        return recommendRepository.findByMemberIdAndId(memberId, recommendId)
            .orElseThrow(IllegalArgumentException::new)
            .getCreatedAt();
    }

    public Page<CompetitionResponseDto> recommendAllByAttributes(
        final Long memberId,
        final Long recommendId,
        final Pageable pageable) {

        return competitionRepository
            .findAllByIds(findIdsByAttributes(memberId, recommendId), pageable)
            .map(CompetitionResponseDto::new);
    }

    private List<Long> findIdsByAttributes(Long memberId, Long recommendId) {
        List<FrequentCompetitionAttribute> attributes
            = recommendRepository.findAttributesByMemberIdAndId(memberId, recommendId);

        Set<Long> competitions = new HashSet<>();
        for (FrequentCompetitionAttribute attribute : attributes) {
            competitions.addAll(findCompetitionIdsByAttribute(attribute));
        }
        return competitions.stream()
            .filter((id) -> !bookmarkService.isBookmarked(memberId, id))
            .collect(Collectors.toList());
    }

    private List<Long> findCompetitionIdsByAttribute(FrequentCompetitionAttribute attribute) {
        List<Competition> allByAttributes = competitionRepository.findAllByAttributes(
            attribute.getApplicantName(),
            attribute.getAwardBenefitName(),
            attribute.getAwardScaleName()
        );
        return allByAttributes.stream()
            .map(Competition::getId)
            .collect(Collectors.toList());
    }

    public List<FrequentCompetitionAttributeDto> findFrequentAttributes(final Long memberId,
        final Long recommendId) {
        return recommendRepository
            .findAttributesByMemberIdAndId(memberId, recommendId)
            .stream()
            .map(FrequentCompetitionAttributeDto::new)
            .collect(Collectors.toList());
    }

    public Page<CompetitionResponseDto> recommendAllBySkills(
        final long memberId,
        final Long recommendId,
        final Pageable pageable) {

        return competitionRepository
            .findAllByIds(findIdsBySkills(memberId, recommendId), pageable)
            .map(CompetitionResponseDto::new);
    }

    private List<Long> findIdsBySkills(final long memberId,
        final long recommendId) {

        List<FrequentSkill> frequentSkills
            = recommendRepository.findSkillsByMemberIdAndId(memberId, recommendId);

        Set<Long> competitions = new HashSet<>();
        for (FrequentSkill skill : frequentSkills) {
            competitions.addAll(findCompetitionIdsBySkill(skill));
        }
        return competitions.stream()
            .filter((id) -> !bookmarkService.isBookmarked(memberId, id))
            .collect(Collectors.toList());
    }

    private List<Long> findCompetitionIdsBySkill(final FrequentSkill frequentSkill) {
        List<Competition> allBySkills = competitionRepository.findAllBySkills(
            frequentSkill.getSkillName()
        );
        return allBySkills.stream()
            .map(Competition::getId)
            .collect(Collectors.toList());
    }

    public List<MostFrequentSkillDto> findMostFrequentSkills(final long memberId,
        final long recommendId) {
        return recommendRepository
            .findSkillsByMemberIdAndId(memberId, recommendId)
            .stream()
            .map(MostFrequentSkillDto::new)
            .collect(Collectors.toList());
    }

    public boolean existsRecommendationByMemberId(final Long memberId) {
        return recommendRepository.existsByMemberId(memberId);
    }
}
