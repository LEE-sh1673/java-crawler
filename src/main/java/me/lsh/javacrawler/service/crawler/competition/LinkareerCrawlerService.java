package me.lsh.javacrawler.service.crawler.competition;

import static me.lsh.javacrawler.domain.event.EventProvider.LINKAREER;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.crawler.CompetitionCrawlerReport;
import me.lsh.javacrawler.domain.crawler.competition.CompetitionCrawler;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.parser.exception.WebCrawlerParseException;
import me.lsh.javacrawler.repository.event.competition.CompetitionRepository;
import me.lsh.javacrawler.service.dto.CompetitionUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkareerCrawlerService implements CompetitionCrawlerService {

    private static final String COMPETITION_URL = "https://linkareer.com/activity/";

    private final CompetitionRepository repository;

    private final CompetitionCrawler crawler;

    private final CompetitionCrawlerReport report;

    @Override
    @Transactional
    public void crawlAll() {
        List<Integer> links = crawlLinks();

        if (isRecentlyUpdated(links)) {
            report.report("페이지 크롤링 X (이유: 이미 최근 갱신된 상태)");
            return;
        }
        links.forEach(this::crawlPage);
    }

    private List<Integer> crawlLinks() {
        List<Integer> links = crawler.crawlLinks();
        log.info("링커리어 페이지 링크 크롤링 = {}", links);
        report.report("페이지 내 공고 아이디 크롤링: " + links);
        report.report("크롤링된 공고 개수: " + links.size());

        if (links.isEmpty()) {
            throw new WebCrawlerParseException("Can not parse page links. (elements = 0)",
                crawler.getClass());
        }
        return links;
    }

    private boolean isRecentlyUpdated(final List<Integer> links) {
        Long recentPageId = repository.findTopByPageId();
        return recentPageId != null && recentPageId >= links.get(0);
    }

    private void crawlPage(final int pageId) {
        Competition page = crawler.crawl(COMPETITION_URL + pageId);
        report.report("페이지 크롤링 :  [" + page.getPageId() + "] " + page.getTitle());
        updatePage(page);
    }

    private void updatePage(final Competition page) {
        Optional<Competition> byTitleAndProvider
            = repository.findByTitleAndProvider(page.getTitle(), LINKAREER);

        if (byTitleAndProvider.isEmpty()) {
            repository.save(page);
            report.report("\t-> 페이지가 저장됨");
            return;
        }
        updatePageWith(page, byTitleAndProvider.get());
    }

    private void updatePageWith(final Competition page, final Competition byTitleAndProvider) {
        if (isUpdated(page, byTitleAndProvider)) {
            log.info("갱신할 공모전 정보 = {}", page.getTitle());
            report.report("\t-> 페이지가 갱신됨");
            page.update(new CompetitionUpdateDto(byTitleAndProvider));
        }
    }

    private boolean isUpdated(final Competition page, final Competition crawledPage) {
        return !page.matchOrganizer(crawledPage.getOrganizer())
            || !page.matchThumbnail(crawledPage.getThumbnail())
            || !page.matchPageId(crawledPage.getPageId())
            || !page.matchAwardAmount(crawledPage.getAwardAmount())
            || !page.matchSkill(crawledPage.getSkills());
    }
}
