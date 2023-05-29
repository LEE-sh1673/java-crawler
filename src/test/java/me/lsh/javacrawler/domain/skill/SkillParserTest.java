package me.lsh.javacrawler.domain.skill;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import me.lsh.javacrawler.parser.RegexMatcher;
import me.lsh.javacrawler.parser.RegexSkillParser;
import me.lsh.javacrawler.parser.SkillParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = { RegexSkillParser.class, RegexMatcher.class })
class SkillParserTest {

    @Autowired
    private SkillParser parser;

    @Test
    void url_파싱() {
        // given
        String content = "- 해양경찰청 해양환경정화 방제선 탑승 기회 제공 (수상자 일부)※ 자세한 내용은 공모전사이트 참고■ 지원 방법\u200B가)"
            + "슬로건 <당당히, 바다와 마주하다>를 실천하기 위해 (     _)를 호소 (실천, 제안)하고자 한다.  : (    )를 참여"
            + "주제 가,나,다 중에서 선택하여 채우고 내용을 설명하는 영상 제작.나) 각자의 내용을 담은 피켓을 만드시고 피켓을 설명하는 영상응모"
            + "(길이 : 30초-3분 )다) 피켓은 형태의 물리적, 공간적 제한이 없음.라) 글, 노래, 댄스, 드라마, 그림 등 표현할 수 있는 모든 방법으로"
            + "자유롭게 표현 자신의 피켓을 설명하는 영상을 참가신청서와 함께 해당 메일로 제출."
            + "- 업로드 위치 : 주니어해양컨퍼런스 홈페이지/프로그램/해양피켓챌린지/참가방법/신청링크"
            + "■ 문의 사항 : 2023 주니어해양컨퍼런스 공식 홈페이지(http://juniogitrocean.org)"
            + "[문의] 게시판 또는 공식 인스타그램(juniorocean_2023) DM■ 홍보 영상 : https://youtu.be/yZnJbujAfVY";

        // when
        Set<Skill> skills = parser.parse(content);

        // then
        Assertions.assertThat(skills)
            .containsOnly(Skill.NO_SKILL);
    }

    @Test
    void 이메일_파싱() {
        // given
        String content = "외교부 / 한국국제협력단, 한국국제교류재단, 한·아프리카재단기업형태공공기관/공기업참여대상대상 제한 없음"
            + "시상규모800만 원접수기간23.5.10 ~ 23.7.4홈페이지https://www.mkotlinoaijavafa.go.kr/www/brd/m_4075/view.do?seq=368913활동혜택기타,"
            + "시상규모를 축소 또는 온라인 참가 신청 및 제출, 관련 내용은 대회 홈페이지(http://www.k-hacaipythonkajavathon.com/) 참고"
            + "대학생 대외활동 공모전 채용 사이트 링커리어 https://linkareer.com/ 변경할 수 있음- 공모내역은 향후 외교 공공데이터 정책에 활용될 수 있음- 타인의 지식재산권을 침해하거나"
            + " 유사대회23.5.10 ~ 23.7.4openaidatapython@k-af.or.krd/m_4075/view.do?se에서 중복 수상한 경우 입상 취소- 경진대회에 제출된 자료의 저작권은 출품한 저작자에게 있으며, 주최기관 및 주"
            + "관기관은 사용권을 가짐[문의]- 02-2100-1763, 02-722-4063- 이메일 : openaidatapython@k-af.or.kr";

        // when
        Set<Skill> skills = parser.parse(content);

        // then
        Assertions.assertThat(skills)
            .containsOnly(Skill.NO_SKILL);
    }

    @Test
    void 스킬_파싱() {
        // given
        String skillSet = "※ AI, 블록체인, 데이터, IoT, 네트워크 등";

        // when
        Set<Skill> skills = parser.parse(skillSet);

        // then
        assertEquals(3, skills.size());
        Assertions.assertThat(skills)
            .containsOnly(Skill.AI, Skill.BLOCK_CHAIN, Skill.LOT);
    }

    @ParameterizedTest
    @MethodSource("provideEventContents")
    void 스킬_파싱_본문(final String content, final List<Skill> expected){
        Assertions.assertThat(parser.parse(content)).containsAll(expected);
    }

    private static Stream<Arguments> provideEventContents() {
        return Stream.of(
            Arguments.of(
            "▪ 지원자격- 대학교 재학중인 학부생 또는 대학원생 (개인 또는 팀)▪ 접수방법"
                + "- 대회 홈페이지 (https://bit.ly/3UQZJ3p)▪ 출품규격- 예선: MATLAB을 활용한 AI모델 개요 제출- "
                + "본선: 예선을 통과한 AI 모델에 대한 동영상 제출* 예선 통과 후 본선에서 동영상을 제출한 모든 팀에 커피 기프티콘을 제공합니다.",
                List.of(Skill.AI, Skill.MATLAB)
            ),
            Arguments.of("○ 교육내용 : \n"
                + "- DB(오라클), 파이썬, Django, 빅데이터 분석 알고리즘, \n"
                + "AI 머신러닝 알고리즘, AWS, 도커, 쿠버네티스, MSA, \n"
                + "기본역량프로젝트, 핵심역량프로젝트, 실무역량프로젝트 등\n"
                + "(수료후 진로 : Full-Stack개발자, AI서비스 개발자, 빅데이터 분석 개발자, 클라우드 서비스 개발자",
                List.of(Skill.ORACLE, Skill.PYTHON, Skill.DJANGO,
                    Skill.AI, Skill.CLOUD, Skill.DOCKER, Skill.KUBERNETES, Skill.MSA))
        );
    }
}