package me.lsh.javacrawler.controller.member;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.common.config.auth.SessionMember;
import me.lsh.javacrawler.controller.member.dto.SkillSearch;
import me.lsh.javacrawler.service.bookmark.BookmarkService;
import me.lsh.javacrawler.service.member.MemberService;
import me.lsh.javacrawler.service.recommend.RecommendCompetitionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final HttpSession httpSession;

    private final MemberService memberService;

    private final BookmarkService bookmarkService;

    private final RecommendCompetitionService recommendCompetitionService;

    @GetMapping("/profile")
    public String profile(
        @ModelAttribute("skillSearch") final SkillSearch skillSearch,
        @Qualifier("competition") @PageableDefault(size = 3) final Pageable compPageable,
        @Qualifier("moim") @PageableDefault(size = 5) final Pageable moimPageable,
        final Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            model.addAttribute("member", memberService.findOne(member.getId()));
            model.addAttribute("competitions", bookmarkService.findCompetitions(member.getId(),
                compPageable));
            model.addAttribute("hasRecommendCompetition",
                recommendCompetitionService.existsRecommendationByMemberId(member.getId()));
            model.addAttribute("moims", bookmarkService.findMoims(member.getId(), moimPageable));
        }
        return "profile";
    }
}
