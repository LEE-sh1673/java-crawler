package me.lsh.javacrawler.domain.skill;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SkillTest {

    @Test
    void containsLabelInEnglish_then_False() {
        assertTrue(Skill.PYTHON.matchLabel("python을"));
        assertTrue(Skill.PYTHON.matchLabel("python또는"));
        assertTrue(Skill.MONGODB.matchLabel("또는tepython몽고디비dbsfko한"));
    }
}