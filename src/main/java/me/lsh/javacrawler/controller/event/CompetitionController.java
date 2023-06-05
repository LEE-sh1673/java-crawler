package me.lsh.javacrawler.controller.event;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.common.config.auth.SessionMember;
import me.lsh.javacrawler.controller.event.dto.CompetitionSearch;
import me.lsh.javacrawler.service.bookmark.BookmarkService;
import me.lsh.javacrawler.service.event.CompetitionService;
import me.lsh.javacrawler.service.dto.CompetitionResponseDto;
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
@RequestMapping("/competitions")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    private final MemberService memberService;

    private final BookmarkService bookmarkService;

    private final HttpSession httpSession;

    @GetMapping
    public String competitions(
        @ModelAttribute("searchOption") final CompetitionSearch searchOption,
        @PageableDefault(size = 12) final Pageable pageable,
        final Model model) {

        model.addAttribute("paging",
            competitionService.findAllByCriteria(searchOption, pageable));

        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            model.addAttribute("member", memberService.findOne(member.getId()));
        }
        return "competitions";
    }

    @GetMapping("{id}")
    public String competition(@PathVariable final Long id, final Model model) {
        CompetitionResponseDto comp = competitionService.findOne(id);
        competitionService.updateView(id);
        model.addAttribute("competition", comp);

        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
            model.addAttribute("isBookmarked",
                bookmarkService.isBookmarked(member.getId(), comp.getId()));
        }
        return "competition";
    }
}
