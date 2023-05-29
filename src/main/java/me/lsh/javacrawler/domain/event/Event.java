package me.lsh.javacrawler.domain.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lsh.javacrawler.domain.BaseTimeEntity;
import me.lsh.javacrawler.domain.skill.Skill;
import me.lsh.javacrawler.service.dto.EventUpdateDto;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime startedAt;

    protected LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    private RecruitmentStatus status;  // OPEN, CLOSE

    @ElementCollection(targetClass = ApplicantType.class)
    @CollectionTable(name = "event_applicants", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "applicants", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<ApplicantType> applicants;  // NO_LIMIT, UNIV_STUDENT, TEENAGER

    @ElementCollection(targetClass = Skill.class)
    @CollectionTable(name = "event_skills", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "eventSkills", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;

    @Enumerated(EnumType.STRING)
    private EventProvider provider;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String organizer;

    private String thumbnail;

    private String originalLink;

    private int viewCount;

    private int bookmarkCount;

    public boolean matchThumbnail(final String thumbnail) {
        return this.thumbnail.equals(thumbnail);
    }

    public boolean matchOrganizer(final String organizer) {
        return this.organizer.equals(organizer);
    }

    public void update(final EventUpdateDto other) {
        this.title = other.getTitle();
        this.thumbnail = other.getThumbnail();
        this.startedAt = other.getStartedAt();
        this.expiredAt = other.getExpiredAt();
        this.skills = other.getSkills();
        this.content = other.getContent();
    }

    public boolean matchSkill(final Set<Skill> skills) {
        return this.skills.containsAll(skills);
    }

    public boolean isExpired() {
        return this.expiredAt.isBefore(LocalDateTime.now());
    }

    public Long calculateDDay() {
        LocalDateTime currentDate = LocalDateTime.now();
        return ChronoUnit.DAYS.between(currentDate.toLocalDate(), getExpiredAt());
    }

    public void increaseBookmarkCount() {
        this.bookmarkCount += 1;
    }

    public void decreaseBookmarkCount() {
        if (this.bookmarkCount > 0) {
            this.bookmarkCount -= 1;
        }
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", applicants=" + applicants +
            ", eventType=" + eventType +
            ", organizer='" + organizer + '\'' +
            '}';
    }
}
