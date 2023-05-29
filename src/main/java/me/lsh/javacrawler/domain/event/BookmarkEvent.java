package me.lsh.javacrawler.domain.event;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import me.lsh.javacrawler.domain.BaseTimeEntity;
import me.lsh.javacrawler.domain.member.Member;

@Entity
@Getter
public class BookmarkEvent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    // 북마크 생성
    public static BookmarkEvent createBookmark(final Member member, final Event event) {
        // 북마크 수 증가
        event.increaseBookmarkCount();
        BookmarkEvent bookmarkEvent = new BookmarkEvent();
        bookmarkEvent.setMember(member);
        bookmarkEvent.setEvent(event);
        return bookmarkEvent;
    }

    private void setEvent(final Event event) {
        this.event = event;
    }

    private void setMember(final Member member) {
        this.member = member;
        member.addBookmark(this);
    }

    // 북마크 취소
    public void cancel() {
        getEvent().decreaseBookmarkCount();
        getMember().removeBookmark(this);
    }

    public boolean isCompetition() {
        return getEvent().getEventType()
            .isCompetition();
    }

    public boolean isMoim() {
        return getEvent().getEventType()
            .isMoim();
    }
}
