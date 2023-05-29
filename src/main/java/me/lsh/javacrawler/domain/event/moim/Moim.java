package me.lsh.javacrawler.domain.event.moim;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.Event;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.EventType;
import me.lsh.javacrawler.domain.event.RecruitmentStatus;
import me.lsh.javacrawler.domain.skill.Skill;
import me.lsh.javacrawler.service.dto.MoimUpdateDto;

@Entity
@Getter
@DiscriminatorValue("m")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Moim extends Event {

    private int pageId;

    private String location;

    private boolean feeRequired;

    private String duration;

    @Enumerated(EnumType.STRING)
    private MoimType moimType;

    @Builder
    public Moim(final Long id, final String title, final String content,
        final LocalDateTime startedAt, final LocalDateTime expiredAt,
        final RecruitmentStatus status, final Set<ApplicantType> applicants,
        final Set<Skill> skills,
        final EventProvider provider, final String organizer,
        final String thumbnail, final String originalLink, final int viewCount,
        final int bookmarkCount, final int pageId,
        final String location, final boolean feeRequired, final String duration,
        final MoimType moimType) {

        super(id, title, content,
            startedAt, expiredAt, status,
            applicants, skills, provider,
            EventType.MOIM, organizer, thumbnail,
            originalLink, viewCount, bookmarkCount);

        this.pageId = pageId;
        this.location = location;
        this.feeRequired = feeRequired;
        this.duration = duration;
        this.moimType = moimType;
    }

    public boolean matchPageId(final int pageId) {
        return this.pageId == pageId;
    }

    public boolean matchLocation(final String location) {
        return location != null && location.equals(this.location);
    }

    public void update(final MoimUpdateDto moimDto) {
        super.update(moimDto);
        this.location = moimDto.getLocation();
        this.moimType = moimDto.getMoimType();
        this.duration = moimDto.getDuration();
        this.feeRequired = moimDto.isFeeRequired();
    }
}
