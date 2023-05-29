package me.lsh.javacrawler.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.moim.MoimType;
import me.lsh.javacrawler.domain.skill.Skill;
import me.lsh.javacrawler.repository.event.DateOption;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class MoimSearch {

    private String name;

    private DateOption dateOption;

    private List<ApplicantType> applicantTypes;

    private boolean isFeeRequired;

    private String sort;

    private MoimType moimType;

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

    public boolean hasMoimType() {
        return moimType != null;
    }

    public boolean hasSkills() {
        return skills != null && !skills.isEmpty();
    }

    public boolean hasSort() {
        return sort != null;
    }

    @Override
    public String toString() {
        return "MoimSearch{" +
            "name='" + name + '\'' +
            ", dateOption=" + dateOption +
            ", applicantTypes=" + applicantTypes +
            ", isFeeRequired=" + isFeeRequired +
            ", sort='" + sort + '\'' +
            ", moimType=" + moimType +
            ", skills=" + skills +
            '}';
    }
}
