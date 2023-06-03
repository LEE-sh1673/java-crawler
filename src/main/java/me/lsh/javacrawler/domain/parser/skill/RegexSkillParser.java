package me.lsh.javacrawler.domain.parser.skill;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.skill.Skill;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegexSkillParser implements SkillParser {

    private static final String REPLACE_LETTERS_REGEX = "[^a-zA-Z0-9가-힣.@:/_=\\-?&%+$]";

    private final RegexMatcher matcher;

    @Override
    public Set<Skill> parse(final String content) {
        Set<Skill> skills = parseSkills(content);
        log.info("파싱된 기술 집합 = {} / 기술 개수 = {}", skills, skills.size());

        if (skills.size() == 0) {
            skills.add(Skill.NO_SKILL);
        }
        return skills;
    }

    private Set<Skill> parseSkills(final String content) {
        List<String> words = parseContent(content);
//        log.info("split words 파싱 = {}", words);
        return Arrays.stream(Skill.values())
            .filter(skill -> matcher.matchSkill(words, skill))
            .collect(Collectors.toSet());
    }

    private List<String> parseContent(final String content) {
        return Arrays.stream(splitByWhitespaceLetters(content))
            .filter(this::isValidWord)
            .collect(Collectors.toList());
    }

    private String[] splitByWhitespaceLetters(final String content) {
        return replaceSpecialLetters(content).split("\\s");
    }

    private String replaceSpecialLetters(final String word) {
        return word.replaceAll(REPLACE_LETTERS_REGEX, " ");
    }

    private boolean isValidWord(final String word) {
        return !word.isBlank()
            && !matcher.matchOnlyLetters(word)
            && !matcher.matchEmail(word)
            && !matcher.matchURL(word);
    }
}
