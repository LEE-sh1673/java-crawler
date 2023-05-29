package me.lsh.javacrawler.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.config.auth.SessionMember;
import me.lsh.javacrawler.service.member.MemberService;
import me.lsh.javacrawler.service.recommend.RecommendService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recommends")
@RequiredArgsConstructor
public class RecommendController {

    private final MemberService memberService;

    private final RecommendService recommendService;

    private final HttpSession httpSession;

    @GetMapping
    public String recommend(
        final Model model,
        @Qualifier("bk") @PageableDefault(size = 5) final Pageable bkPageable,
        @Qualifier("skill") @PageableDefault(size = 5) final Pageable skillPageable) {

        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            Long memberId = member.getId();
            model.addAttribute("member", memberService.findOne(memberId));
            model.addAttribute("competitionAttributes",
                recommendService.findMostFrequentCompetitionAttribute(memberId));
            model.addAttribute("competitions",
                recommendService.recommendCompetitions(memberId, bkPageable));
            model.addAttribute("frequentSkills",
                recommendService.getMostFrequentCompetitionSkillsInBookmark(memberId));
            model.addAttribute("skillCompetitions",
                recommendService.recommendCompetitionsBySkill(memberId, skillPageable));
        }
        return "recommends";
    }
}
