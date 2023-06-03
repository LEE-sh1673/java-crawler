package me.lsh.javacrawler.controller.event;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import me.lsh.javacrawler.controller.member.MemberController;
import me.lsh.javacrawler.controller.recommend.CompetitionRecommendController;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.AwardScale;
import me.lsh.javacrawler.domain.event.moim.MoimType;
import me.lsh.javacrawler.domain.skill.Skill;
import me.lsh.javacrawler.controller.event.dto.DateOption;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = {CompetitionController.class, MoimController.class, MemberController.class, CompetitionRecommendController.class})
public class EventControllerAdvice {

    @ModelAttribute("dateOptions")
    public DateOption[] getDateOptions() {
        return DateOption.values();
    }

    @ModelAttribute("moimTypes")
    public MoimType[] getMoimTypes() {
        return MoimType.values();
    }

    @ModelAttribute("applicantTypes")
    public Map<Integer, ApplicantType> getApplicantTypes() {
        return toEnumMap(ApplicantType.values(), ApplicantType::getCode);
    }

    @ModelAttribute("skills")
    public Map<Integer, Skill> skills() {
        return toEnumMap(Skill.values(), Skill::getCode);
    }

    @ModelAttribute("awardScales")
    public Map<Integer, AwardScale> getAwardScales() {
        return toEnumMap(AwardScale.values(), AwardScale::getCode);
    }

    @ModelAttribute("awardBenefits")
    public Map<Integer, AwardBenefit> getAwardBenefits() {
        return toEnumMap(AwardBenefit.values(), AwardBenefit::getCode);
    }

    private <T> Map<Integer, T> toEnumMap(final T[] array,
        final Function<? super T, Integer> keyMapper) {

        return Arrays.stream(array)
            .collect(Collectors.toMap(
                keyMapper,
                Function.identity(),
                (a, b) -> a,
                LinkedHashMap::new));
    }
}
