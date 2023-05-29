package me.lsh.javacrawler.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OAuth2LoginController {

    @GetMapping("/login/{registrationId}")
    public String socialLogin(final @PathVariable String registrationId,
        final HttpServletRequest request) {
        String targetUrl = getTargetUrl(request);
        request.getSession().setAttribute("targetUrl", targetUrl);
        return "redirect:/oauth2/authorization/" + registrationId;
    }

    private String getTargetUrl(final HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer != null) {
            return referer;
        }
        return "/";
    }
}
