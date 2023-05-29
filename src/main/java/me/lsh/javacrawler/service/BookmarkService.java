package me.lsh.javacrawler.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.domain.event.BookmarkEvent;
import me.lsh.javacrawler.domain.event.Event;
import me.lsh.javacrawler.domain.member.Member;
import me.lsh.javacrawler.repository.event.EventRepository;
import me.lsh.javacrawler.repository.member.BookmarkRepository;
import me.lsh.javacrawler.repository.member.MemberRepository;
import me.lsh.javacrawler.service.dto.BookmarkRequestSaveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookmarkService {

    private final MemberRepository memberRepository;

    private final BookmarkRepository bookmarkRepository;

    private final EventRepository eventRepository;

    @Transactional
    public void bookmark(final BookmarkRequestSaveDto requestDto) {
        Event event = eventRepository.findById(requestDto.getEventId())
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 공고입니다." + requestDto.getEventId()));

        Member member = memberRepository.findById(requestDto.getMemberId())
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다." + requestDto.getMemberId()));

        // 이미 북마크되어 있으면 좋아요 취소
        Optional<BookmarkEvent> findBookmark = bookmarkRepository.findByMemberAndEvent(member,
            event);

        if (findBookmark.isPresent()) {
            BookmarkEvent bookmarkEvent = findBookmark.get();
            bookmarkEvent.cancel();
            bookmarkRepository.delete(bookmarkEvent);
        } else {
            BookmarkEvent bookmark = BookmarkEvent.createBookmark(member, event);
            bookmarkRepository.save(bookmark);
        }
    }

    @Transactional
    public void cancel(final Long id) {
        BookmarkEvent bookmarkEvent = findOne(id);
        bookmarkEvent.cancel();
        bookmarkRepository.delete(bookmarkEvent);
    }

    public Page<BookmarkEvent> findCompetitions(final Long memberId, final Pageable pageable) {
        return bookmarkRepository.findCompetitions(memberId, pageable);
    }

    public Page<BookmarkEvent> findMoims(final Long memberId, final Pageable pageable) {
        return bookmarkRepository.findMoims(memberId, pageable);
    }

    public BookmarkEvent findOne(final Long id) {
        return bookmarkRepository.findById(id)
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 북마크입니다." + id));
    }

    public boolean isBookmarked(final Long memberId, final Long eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 공고입니다." + eventId));

        Member member = memberRepository.findById(memberId)
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다." + memberId));

        return bookmarkRepository.existsByMemberAndEvent(member, event);
    }
}
