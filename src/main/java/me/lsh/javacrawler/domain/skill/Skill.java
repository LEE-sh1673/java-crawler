package me.lsh.javacrawler.domain.skill;

import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public enum Skill {
    JAVA(1, List.of("java", "자바")),
    PYTHON(2, List.of("python", "파이썬")),
    AI(3, List.of("ai", "딥러닝", "인공지능")),
    BLOCK_CHAIN(4, List.of("blockchain", "블록체인", "가상화폐")),
    LOT(5, List.of("iot", "사물인터넷")),
    MATLAB(6, List.of("matlab")),
    CLOUD(7, List.of("cloud", "클라우드", "aws", "gcp", "azure")),
    MSA(8, List.of("msa", "마이크로서비스")),
    DOCKER(9, List.of("docker", "도커")),
    KUBERNETES(10, List.of("kubernetes", "쿠버네티스")),
    LINUX(11, List.of("linux", "리눅스")),
    ORACLE(12, List.of("oracle", "오라클")),
    MYSQL(13, List.of("mysql")),
    GIT(14, List.of("git")),
    GITHUB(15, List.of("github", "깃허브")),
    SPRING_BOOT(16, List.of("spring boot", "spring-boot", "스프링 부트")),
    SPRING(17, List.of("spring", "스프링")),
    DJANGO(18, List.of("django", "장고")),
    REACT(19, List.of("react", "리엑트")),
    NEXTJS(20, List.of("next.js", "next js", "nextjs", "넥스트")),
    VUE(21, List.of("vue.js", "vuejs", "vue js")),
    DART(22, List.of("dart")),
    ANGULAR(23, List.of("angular", "앵귤러")),
    MONGODB(24, List.of("mongodb", "몽고디비")),
    CPP(25, List.of("c++", "cpp")),
    KOTLIN(26, List.of("kotlin", "코틀린")),
    SWIFT(27, List.of("swift", "스위프트")),
    CSHARP(28, List.of("c#", "csharp")),
    JAVASCRIPT(29, List.of("javascript", "자바스크립트")),
    TYPESCRIPT(30, List.of("typescript", "타입스크립트")),
    BACKEND(31, List.of("backend", "back-end", "백엔드")),
    FRONTEND(32, List.of("frontend", "front-end", "프론트엔드", "프론트")),
    FULLSTACK(33, List.of("fullstack", "full-stack", "풀스택")),
    NO_SKILL(34, List.of("관련기술 없음"));

    private final int code;

    private final List<String> labels;

    private static final String SKILL_REGEX_PREFIX = "([가-힣]*)?";

    Skill(final int code, final List<String> labels) {
        this.code = code;
        this.labels = labels;
    }

    public boolean matchLabel(final String skillLabel) {
        return skillLabel != null
            && labels.stream().anyMatch(skillLabel::contains);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getName() {
        return labels.get(0).toUpperCase();
    }

    public boolean isNoSkill() {
        return this == NO_SKILL;
    }
}