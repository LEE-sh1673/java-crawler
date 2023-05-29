package me.lsh.javacrawler.parser.event.moim.festa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.event.ApplicantType;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.event.moim.MoimType;
import me.lsh.javacrawler.parser.SkillParser;
import me.lsh.javacrawler.parser.event.exception.WebCrawlerParsingException;
import me.lsh.javacrawler.parser.event.moim.MoimContentParser;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FestaContentParser implements MoimContentParser {

    private final SkillParser skillParser;

    @Override
    public Moim parse(final String url, final Document document) {
        RootElementParser parser = new RootElementParser(document);
        return Moim.builder()
            .originalLink(url)
            .provider(EventProvider.FESTA)
            .pageId(parsePageId(url))
            .title(parser.parseTitle())
            .content(parser.parseContentHTML())
            .organizer(parser.parseOrganizer())
            .location(parser.parseLocation())
            .thumbnail(parser.parseThumbnail())
            .startedAt(parser.parseStartedAt())
            .expiredAt(parser.parseExpiredAt())
            .applicants(Set.of(ApplicantType.NO_LIMIT))
            .feeRequired(parser.parseFeeRequired())
            .moimType(MoimType.OFFLINE)
            .duration(parser.parseDuration())
            .skills(skillParser.parse(parser.parseContent()))
            .build();
    }

    public int parsePageId(final String url) {
        int pageId = Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
        log.info("이벤트 페이지 id 파싱 = {}", pageId);
        return pageId;
    }
}
