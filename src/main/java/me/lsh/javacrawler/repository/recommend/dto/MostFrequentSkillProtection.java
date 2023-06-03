package me.lsh.javacrawler.repository.recommend.dto;

import me.lsh.javacrawler.domain.skill.Skill;

public interface MostFrequentSkillProtection {

    Skill getSkill();

    int getCount();
}
