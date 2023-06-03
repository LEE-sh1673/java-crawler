package me.lsh.javacrawler.common.config.auth;

import java.io.Serializable;
import lombok.Getter;
import me.lsh.javacrawler.domain.member.Job;
import me.lsh.javacrawler.domain.member.Member;

@Getter
public class SessionMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String name;

    private final String email;

    private final String picture;

    private final Job job;

    public SessionMember(final Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.job = member.getJob();
    }
}
