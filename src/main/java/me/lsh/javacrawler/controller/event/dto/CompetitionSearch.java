package me.lsh.javacrawler.controller.event.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.competition.AwardBenefit;
import me.lsh.javacrawler.domain.event.competition.AwardScale;
import me.lsh.javacrawler.domain.skill.Skill;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class CompetitionSearch {

    private String name;

    private DateOption dateOption;

    private List<ApplicantType> applicantTypes;

    private AwardScale awardScale;

    private List<AwardBenefit> awardBenefits;

    private String sort;

    private List<Skill> skills;

    public boolean hasName() {
        return StringUtils.hasText(name);
    }

    public boolean hasDateOption() {
        return dateOption != null;
    }

    public boolean hasApplicantType() {
        return applicantTypes != null && !applicantTypes.isEmpty();
    }

    public boolean hasAwardScale() {
        return awardScale != null;
    }

    public boolean hasAwardBenefit() {
        return awardBenefits != null && !awardBenefits.isEmpty();
    }

    public boolean hasSkills() {
        return skills != null && !skills.isEmpty();
    }

    public boolean hasSort() {
        return sort != null;
    }

    @Override
    public String toString() {
        return "CompetitionSearch{" +
            "name='" + name + '\'' +
            ", dateOption=" + dateOption +
            ", applicantTypes=" + applicantTypes +
            ", awardScale=" + awardScale +
            ", awardBenefits=" + awardBenefits +
            ", skills=" + skills +
            ", sort=" + sort +
            '}';
    }
}
