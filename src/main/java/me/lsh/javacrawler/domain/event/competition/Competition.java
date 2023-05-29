package me.lsh.javacrawler.domain.event.competition;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.Event;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.EventType;
import me.lsh.javacrawler.domain.event.RecruitmentStatus;
import me.lsh.javacrawler.domain.skill.Skill;
import me.lsh.javacrawler.service.dto.CompetitionUpdateDto;

@Entity
@Setter
@Getter
@DiscriminatorValue("c")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Competition extends Event {

    private int pageId;

    @ElementCollection(targetClass = AwardBenefit.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "competition_benefit", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name="benefits", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<AwardBenefit> awardBenefits;

    private String awardAmount;

    @Enumerated(EnumType.STRING)
    private AwardScale awardScale;

    @Builder
    public Competition(final Long id, final String title, final String content,
        final LocalDateTime startedAt,
        final LocalDateTime expiredAt, final RecruitmentStatus status,
        final Set<ApplicantType> applicants,
        final Set<Skill> skills, final EventProvider provider,
        final String organizer,
        final String thumbnail, final String originalLink, final int viewCount,
        final int bookmarkCount, final int pageId,
        final Set<AwardBenefit> awardBenefits, final String awardAmount,
        final AwardScale awardScale) {

        super(id, title, content,
            startedAt, expiredAt, status,
            applicants, skills, provider,
            EventType.COMPETITION, organizer, thumbnail,
            originalLink, viewCount, bookmarkCount);

        this.pageId = pageId;
        this.awardBenefits = awardBenefits;
        this.awardAmount = awardAmount;
        this.awardScale = awardScale;
    }

    public boolean matchPageId(final int pageId) {
        return this.pageId == pageId;
    }

    public boolean matchAwardAmount(final String awardAmount) {
        return this.awardAmount.equals(awardAmount);
    }

    public void update(final CompetitionUpdateDto competitionDto) {
        super.update(competitionDto);
        this.awardAmount = competitionDto.getAwardAmount();
        this.awardScale = competitionDto.getAwardScale();
        this.awardBenefits = competitionDto.getAwardBenefit();
    }

    @Override
    public String toString() {
        return "Competition{" +
            "pageId=" + pageId +
            ", title=" + super.getTitle() +
            ", awardBenefits=" + awardBenefits +
            ", awardAmount='" + awardAmount + '\'' +
            ", awardScale=" + awardScale +
            '}';
    }
}
