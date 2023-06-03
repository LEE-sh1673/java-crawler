package me.lsh.javacrawler.controller.event;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.common.config.auth.SessionMember;
import me.lsh.javacrawler.controller.event.dto.MoimSearch;
import me.lsh.javacrawler.service.bookmark.BookmarkService;
import me.lsh.javacrawler.service.event.MoimService;
import me.lsh.javacrawler.service.dto.MoimResponseDto;
import me.lsh.javacrawler.service.member.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/moims")
@RequiredArgsConstructor
public class MoimController {

    private final MoimService moimService;

    private final MemberService memberService;

    private final BookmarkService bookmarkService;

    private final HttpSession httpSession;

    @GetMapping
    public String moims(
        @ModelAttribute("searchOption") final MoimSearch searchOption,
        @PageableDefault(size = 12) final Pageable pageable,
        final Model model) {

        model.addAttribute("paging",
            moimService.findCompetitions(searchOption, pageable));

        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", memberService.findOne(member.getId()));
        }
        return "moims";
    }

    @GetMapping("{id}")
    public String moim(@PathVariable final Long id, final Model model) {
        MoimResponseDto moim = moimService.findOne(id);
        model.addAttribute("moim", moim);

        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
            model.addAttribute("isBookmarked",
                bookmarkService.isBookmarked(member.getId(), moim.getId()));
        }
        return "moim";
    }
}
