package me.lsh.javacrawler.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.common.config.auth.SessionMember;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(final Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
        }
        return "index";
    }
}
