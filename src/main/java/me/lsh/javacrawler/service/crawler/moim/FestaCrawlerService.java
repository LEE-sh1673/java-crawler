package me.lsh.javacrawler.service.crawler.moim;

import static me.lsh.javacrawler.domain.event.EventProvider.FESTA;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.crawler.MoimCrawlerReport;
import me.lsh.javacrawler.domain.crawler.moim.MoimCrawler;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.parser.exception.WebCrawlerParseException;
import me.lsh.javacrawler.repository.event.moim.MoimRepository;
import me.lsh.javacrawler.service.dto.MoimUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FestaCrawlerService implements MoimCrawlerService {

    private static final String COMPETITION_URL = "https://festa.io/events/";

    private final MoimRepository repository;

    private final MoimCrawler crawler;

    private final MoimCrawlerReport report;

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
        log.info("festa 페이지 링크 크롤링 = {}", links);
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

    private void crawlPage(final Integer link) {
        Moim page = crawler.crawl(COMPETITION_URL + link);
        report.report("페이지 크롤링 :  [" + page.getPageId() + "] " + page.getTitle());
        updateMoim(page);
    }

    private void updateMoim(final Moim page) {
        Optional<Moim> byTitleAndProvider
            = repository.findByTitleAndProvider(page.getTitle(), FESTA);

        if (byTitleAndProvider.isEmpty()) {
            repository.save(page);
            report.report("\t-> 페이지가 저장됨");
            return;
        }
        updatePageWith(page, byTitleAndProvider.get());
    }

    private void updatePageWith(final Moim page, final Moim crawledPage) {
        if (isUpdated(page, crawledPage)) {
            log.info("갱신할 모임 정보 = {}", page.getTitle());
            report.report("\t-> 페이지가 갱신됨");
            page.update(new MoimUpdateDto(crawledPage));
        }
    }

    private boolean isUpdated(final Moim page, final Moim crawledPage) {
        return !page.matchOrganizer(crawledPage.getOrganizer())
            || !page.matchThumbnail(crawledPage.getThumbnail())
            || !page.matchPageId(crawledPage.getPageId())
            || !page.matchLocation(crawledPage.getLocation())
            || !page.matchSkill(crawledPage.getSkills());
    }
}
