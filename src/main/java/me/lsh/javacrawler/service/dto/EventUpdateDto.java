package me.lsh.javacrawler.service.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import me.lsh.javacrawler.domain.event.Event;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
public abstract class EventUpdateDto {

    private String title;

    private String thumbnail;

    private LocalDateTime startedAt;

    private LocalDateTime expiredAt;

    private String content;

    private Set<Skill> skills;

    public EventUpdateDto(final Event event) {
        this.title = event.getTitle();
        this.thumbnail = event.getThumbnail();
        this.startedAt = event.getStartedAt();
        this.expiredAt = event.getExpiredAt();
        this.content = event.getContent();
        this.skills = event.getSkills();
    }
}
