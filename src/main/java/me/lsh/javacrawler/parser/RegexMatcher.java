package me.lsh.javacrawler.parser;

import java.util.List;
import java.util.regex.Pattern;
import me.lsh.javacrawler.domain.skill.Skill;
import org.springframework.stereotype.Component;

@Component
public class RegexMatcher {

    private static final String EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

    private static final String URL_REGEX = "((http|https)://)?(www\\.)?[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}(/[a-zA-Z0-9.#?%=!&'_-]*)*";

    private static final String ONLY_LETTERS_REGEX = "[^a-zA-Z가-힣]";

    private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    private final Pattern urlPattern = Pattern.compile(URL_REGEX);

    public boolean matchEmail(final String word) {
        String w = word.toLowerCase();
        return w.contains("email")
            || emailPattern.matcher(w).find();
    }

    public boolean matchURL(final String word) {
        String w = word.toLowerCase();
        return w.contains("url")
            || urlPattern.matcher(w).find();
    }

    public boolean matchSkill(final List<String> words, final Skill skill) {
        return words.stream()
            .anyMatch(token -> skill.matchLabel(token.toLowerCase()));
    }

    public boolean matchOnlyLetters(final String word) {
        return word.matches(ONLY_LETTERS_REGEX);
    }
}
