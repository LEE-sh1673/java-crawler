package me.lsh.javacrawler.repository.recommend.dto;

import lombok.Getter;
import me.lsh.javacrawler.domain.recommend.FrequentSkill;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
public class MostFrequentSkillDto {

    private final Skill skill;

    private final int count;

    public MostFrequentSkillDto(final FrequentSkill frequentSkill) {
        this.skill = frequentSkill.getSkill();
        this.count = frequentSkill.getCount();
    }
}
