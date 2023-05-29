package me.lsh.javacrawler.config.auth;

import java.io.Serializable;
import lombok.Getter;
import me.lsh.javacrawler.domain.member.Job;
import me.lsh.javacrawler.domain.member.Member;

@Getter
public class SessionMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    private String picture;

    private Job job;

    public SessionMember(final Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.job = member.getJob();
    }
}
