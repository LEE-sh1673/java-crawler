package me.lsh.javacrawler.domain.parser.skill;

import java.util.Set;
import me.lsh.javacrawler.domain.skill.Skill;

public interface SkillParser {

    Set<Skill> parse(final String content);
}
