package me.lsh.javacrawler.controller.member;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.common.config.auth.SessionMember;
import me.lsh.javacrawler.controller.member.dto.SkillSearch;
import me.lsh.javacrawler.service.member.MemberService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberSkillController {

    private final HttpSession httpSession;

    private final MemberService memberService;

    @PutMapping("/api/v1/skills")
    public void updateSkill(@RequestBody final SkillSearch skillSearch) {
        SessionMember member
            = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            memberService.updateSkill(member.getId(), skillSearch);
        }
    }
}
