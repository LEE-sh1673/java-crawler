package me.lsh.javacrawler.controller.exception;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.config.auth.SessionMember;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionAdvice {

    private final HttpSession httpSession;

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(final Exception exception, final Model model) {
        log.error("Exception occur = {}", exception.getMessage());

        SessionMember member = (SessionMember) httpSession.getAttribute("member");
        if (member != null) {
            model.addAttribute("member", member);
        }
        return "404";
    }

    @ExceptionHandler(WebCrawlerParsingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleWebDriverException(final WebCrawlerParsingException e) {
        log.error("Web driver error = {}", e.getMessage());
    }
}
