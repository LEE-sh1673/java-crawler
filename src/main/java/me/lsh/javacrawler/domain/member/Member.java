package me.lsh.javacrawler.domain.member;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lsh.javacrawler.domain.BaseTimeEntity;
import me.lsh.javacrawler.domain.event.BookmarkEvent;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Job job;

    @ElementCollection(targetClass = Skill.class)
    @CollectionTable(name = "member_skills", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "memberSKills", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<BookmarkEvent> bookmarkEvents = new ArrayList<>();

    @Builder
    public Member(String name, String email, String picture, Set<Skill> skills, Role role, Job job) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.skills = skills;
        this.role = role;
        this.job = job;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public void updateJob(final Job job) {
        this.job = job;
    }

    public void updateSkills(final List<Skill> skills) {
        this.skills = new LinkedHashSet<>(skills);
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void addBookmark(final BookmarkEvent bookmarkEvent) {
        bookmarkEvents.add(bookmarkEvent);
    }

    public void removeBookmark(final BookmarkEvent bookmarkEvent) {
        bookmarkEvents.remove(bookmarkEvent);
    }
}
