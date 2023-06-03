package me.lsh.javacrawler.service.recommend.statistics;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.domain.member.Member;
import me.lsh.javacrawler.domain.recommend.FrequentCompetitionAttribute;
import me.lsh.javacrawler.domain.recommend.FrequentSkill;
import me.lsh.javacrawler.domain.recommend.RecommendCompetition;
import me.lsh.javacrawler.repository.member.MemberRepository;
import me.lsh.javacrawler.repository.recommend.FrequentSkillRepository;
import me.lsh.javacrawler.repository.recommend.RecommendCompetitionAttributesRepository;
import me.lsh.javacrawler.repository.recommend.RecommendCompetitionRepository;
import me.lsh.javacrawler.repository.recommend.dto.FrequentCompetitionAttributeProtection;
import me.lsh.javacrawler.repository.recommend.dto.MostFrequentSkillProtection;
import me.lsh.javacrawler.service.bookmark.BookmarkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompetitionStatisticService {

    private final RecommendCompetitionRepository recommendRepository;

    private final RecommendCompetitionAttributesRepository attributesRepository;

    private final FrequentSkillRepository skillRepository;

    private final MemberRepository memberRepository;

    private final BookmarkService bookmarkService;

    @Transactional
    public void collect() {
        memberRepository.findAll()
            .stream()
            .filter(bookmarkService::hasBookmark)
            .forEach(this::collectByMember);
    }

    private void collectByMember(final Member member) {
        RecommendCompetition recommendCompetition = RecommendCompetition.createRecommendCompetition(
            member,
            collectFrequentAttributes(member),
            collectFrequentSkills(member)
        );
        recommendRepository.save(recommendCompetition);
    }

    private List<FrequentCompetitionAttribute> collectFrequentAttributes(final Member member) {
        List<FrequentCompetitionAttributeProtection> attributes
            = attributesRepository.findFrequentAttributes(member.getId());

        List<FrequentCompetitionAttribute> frequentAttributes = new ArrayList<>();

        for (FrequentCompetitionAttributeProtection attributeDto : attributes) {
            FrequentCompetitionAttribute attribute = getAttribute(attributeDto);
            attributesRepository.save(attribute);
            frequentAttributes.add(attribute);
        }
        return frequentAttributes;
    }

    private List<FrequentSkill> collectFrequentSkills(final Member member) {
        List<MostFrequentSkillProtection> frequentSkills
            = skillRepository.findMostFrequentSkills(member.getId());

        List<FrequentSkill> skills = new ArrayList<>();

        for (MostFrequentSkillProtection skill : frequentSkills) {
            FrequentSkill frequentSkill = getSkill(skill);
            skills.add(frequentSkill);
            skillRepository.save(frequentSkill);
        }
        return skills;
    }

    private FrequentSkill getSkill(final MostFrequentSkillProtection skill) {
        return FrequentSkill.builder()
            .skill(skill.getSkill())
            .count(skill.getCount())
            .build();
    }

    private FrequentCompetitionAttribute getAttribute(
        final FrequentCompetitionAttributeProtection attributeDto) {

        return FrequentCompetitionAttribute.builder()
            .applicant(attributeDto.getApplicant())
            .awardScale(attributeDto.getAwardScale())
            .awardBenefit(attributeDto.getAwardBenefit())
            .count(attributeDto.getCount())
            .build();
    }
}
