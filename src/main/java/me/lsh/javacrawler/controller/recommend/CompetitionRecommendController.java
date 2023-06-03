package me.lsh.javacrawler.controller.recommend;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.common.config.auth.SessionMember;
import me.lsh.javacrawler.service.member.MemberService;
import me.lsh.javacrawler.service.recommend.RecommendCompetitionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recommends")
@RequiredArgsConstructor
public class CompetitionRecommendController {

    private final MemberService memberService;

    private final RecommendCompetitionService recommendService;

    private final HttpSession httpSession;

    @GetMapping
    public String recommend(final Model model,
        @PageableDefault(size = 10) final Pageable pageable) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            Long memberId = member.getId();
            model.addAttribute("member", memberService.findOne(memberId));
            model.addAttribute("recommends",
                recommendService.findAllByMemberId(memberId, pageable)
            );
        }
        return "recommends/recommendList";
    }

    @GetMapping("/{recommendId}/competition")
    public String recommendCompetition(
        final Model model,
        @PathVariable final Long recommendId) {

        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            Long memberId = member.getId();
            model.addAttribute("member", memberService.findOne(memberId));
            model.addAttribute("recommendId", recommendId);
            model.addAttribute("startedAt", recommendService.findCreatedAt(memberId, recommendId));
            model.addAttribute("competitionStatistics",
                recommendService.findFrequentAttributes(memberId, recommendId));
            model.addAttribute("frequentSkills",
                recommendService.findMostFrequentSkills(memberId, recommendId));
        }
        return "recommends/competition";
    }

    @GetMapping("/{recommendId}/competition/attributes")
    public String recommend(
        final Model model,
        @PathVariable final Long recommendId,
        @PageableDefault(size = 5) final Pageable pageable) {

        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            Long memberId = member.getId();
            model.addAttribute("member", memberService.findOne(memberId));
            model.addAttribute("recommendId", recommendId);
            model.addAttribute("startedAt", recommendService.findCreatedAt(memberId, recommendId));
            model.addAttribute("competitions",
                recommendService.recommendAllByAttributes(memberId, recommendId, pageable));
        }
        return "recommends/attributes";
    }

    @GetMapping("/{recommendId}/competition/skills")
    public String recommendSkills(
        final Model model,
        @PathVariable final Long recommendId,
        @PageableDefault(size = 5) final Pageable pageable) {

        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            Long memberId = member.getId();
            model.addAttribute("member", memberService.findOne(memberId));
            model.addAttribute("recommendId", recommendId);
            model.addAttribute("startedAt", recommendService.findCreatedAt(memberId, recommendId));
            model.addAttribute("competitions",
                recommendService.recommendAllBySkills(memberId, recommendId, pageable));
        }
        return "recommends/skills";
    }
}
