package me.lsh.javacrawler.domain.parser.event.competition.linkareer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.parser.event.competition.CompetitionContentParser;
import me.lsh.javacrawler.domain.parser.exception.WebCrawlerParseException;
import me.lsh.javacrawler.domain.parser.skill.SkillParser;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LinkareerContentParser implements CompetitionContentParser {

    private final SkillParser skillParser;

    @Override
    public Competition parse(final String url, final Document document) {
        RootElementParser rootParser = new RootElementParser(document);
        AttributeElementParser attributeParser = new AttributeElementParser(document);
        ContentElementParser contentParser = new ContentElementParser(document);

        return Competition.builder()
            .originalLink(url)
            .pageId(parsePageId(url))
            .title(rootParser.parseTitle())
            .organizer(rootParser.parseOrganizer())
            .thumbnail(rootParser.parseThumbnailURL())
            .startedAt(attributeParser.parseStartedAt())
            .expiredAt(attributeParser.parseExpiredAt())
            .applicants(attributeParser.parseApplicantTypes())
            .awardBenefits(attributeParser.parseAwardBenefits())
            .awardScale(attributeParser.parseAwardScale())
            .awardAmount(attributeParser.parseAwardAmount())
            .content(contentParser.parseContentHTML())
            .skills(skillParser.parse(contentParser.parseContent()))
            .provider(EventProvider.LINKAREER)
            .build();
    }

    public int parsePageId(final String url) {
        int pageId = parseId(url);
        log.info("이벤트 페이지 id 파싱 = {}", pageId);
        return pageId;
    }

    private int parseId(final String url) {
        try {
            return Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
        } catch (Exception e) {
            throw new WebCrawlerParseException("Can not parse page id.", this.getClass(), e);
        }
    }
}
