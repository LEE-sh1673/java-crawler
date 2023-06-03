package me.lsh.javacrawler.repository.bookmark;

import java.util.List;
import java.util.Optional;
import me.lsh.javacrawler.domain.bookmark.BookmarkEvent;
import me.lsh.javacrawler.domain.event.Event;
import me.lsh.javacrawler.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository extends JpaRepository<BookmarkEvent, Long> {

    Optional<BookmarkEvent> findByMemberAndEvent(Member member, Event event);

    @Query("delete from BookmarkEvent h where h.event.id = :eventId")
    @Modifying
    void deleteAllByEventId(@Param("eventId") Long eventId);

    boolean existsByMemberAndEvent(final Member member, final Event event);

    List<BookmarkEvent> findAllByMember(final Member member);

    @Query("SELECT be "
        + "FROM BookmarkEvent be " +
        "JOIN be.member m " +
        "WHERE m.id = :memberId " +
        "AND be.event.eventType = 'COMPETITION'" +
        "ORDER BY be.id ASC")
    Page<BookmarkEvent> findCompetitions(@Param("memberId") Long memberId, final Pageable pageable);

    @Query("SELECT be " +
        "FROM BookmarkEvent be " +
        "JOIN be.member m " +
        "WHERE m.id = :memberId " +
        "AND be.event.eventType = 'MOIM'" +
        "ORDER BY be.id ASC")
    Page<BookmarkEvent> findMoims(@Param("memberId") Long memberId, final Pageable pageable);
}
