package me.lsh.javacrawler.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import me.lsh.javacrawler.domain.skill.Skill;

@Setter
@Getter
public class SkillSearch {

    private List<Skill> skills;

    @Override
    public String toString() {
        return "SkillSearch{" +
            "skills=" + skills +
            '}';
    }
}
