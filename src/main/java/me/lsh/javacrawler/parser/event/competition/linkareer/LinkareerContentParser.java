package me.lsh.javacrawler.parser.event.competition.linkareer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.domain.event.EventProvider;
import me.lsh.javacrawler.domain.event.competition.AwardScale;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.parser.SkillParser;
import me.lsh.javacrawler.parser.event.competition.CompetitionContentParser;
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
        int pageId = Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
        log.info("이벤트 페이지 id 파싱 = {}", pageId);
        return pageId;
    }
}
