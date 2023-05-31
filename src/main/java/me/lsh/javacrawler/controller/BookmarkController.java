package me.lsh.javacrawler.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.service.BookmarkService;
import me.lsh.javacrawler.service.dto.BookmarkRequestSaveDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @ResponseBody
    @PostMapping("/api/v1/bookmarks")
    public void save(@RequestBody final BookmarkRequestSaveDto requestSaveDto) {
        bookmarkService.bookmark(requestSaveDto);
    }

    @ResponseBody
    @DeleteMapping("/api/v1/bookmarks/{id}")
    public void cancel(@PathVariable final Long id) {
        bookmarkService.cancel(id);
    }
}
