package me.lsh.javacrawler.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import lombok.Getter;
import me.lsh.javacrawler.domain.member.Job;
import me.lsh.javacrawler.domain.member.Member;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
public class MemberDto {

    private final Long id;

    private final String name;

    private final String email;

    private final String picture;

    private final Job job;

    private final Set<Skill> skills;

    private final LocalDateTime createdAt;

    public MemberDto(final Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.skills = member.getSkills();
        this.job = member.getJob();
        this.createdAt = member.getCreatedAt();
    }

    public boolean hasNoSkill() {
        return this.skills.contains(Skill.NO_SKILL);
    }

    public String getJoinedDate() {
        return this.createdAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
