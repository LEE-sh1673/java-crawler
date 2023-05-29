package me.lsh.javacrawler.service.member;

import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.controller.dto.SkillSearch;
import me.lsh.javacrawler.domain.member.Member;
import me.lsh.javacrawler.repository.member.MemberRepository;
import me.lsh.javacrawler.service.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto findOne(final Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다." + id));
        return new MemberDto(member);
    }

    @Transactional
    public void updateSkill(final Long id, final SkillSearch skillSearch) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다." + id));

        if (member != null) {
            member.updateSkills(skillSearch.getSkills());
        }
    }
}
