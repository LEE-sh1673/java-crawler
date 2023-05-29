package me.lsh.javacrawler.repository.member;

import java.util.Optional;
import me.lsh.javacrawler.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(final String email);
}
