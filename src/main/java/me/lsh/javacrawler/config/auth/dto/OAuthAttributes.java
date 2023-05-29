package me.lsh.javacrawler.config.auth.dto;

import java.util.Map;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import me.lsh.javacrawler.domain.member.Job;
import me.lsh.javacrawler.domain.member.Member;
import me.lsh.javacrawler.domain.member.Role;
import me.lsh.javacrawler.domain.skill.Skill;

@Getter
public class OAuthAttributes {

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;

    private final String name;

    private final String email;

    private final String picture;

    @Builder
    public OAuthAttributes(final Map<String, Object> attributes,
        final String nameAttributeKey, final String name,
        final String email, final String picture) {

        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(final String registrationId,
        final String userNameAttributeName,
        final Map<String, Object> attributes) {

        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        if ("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(final String userNameAttributeName,
        final Map<String, Object> attributes) {

        return OAuthAttributes.builder()
            .name((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .picture((String) attributes.get("picture"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    private static OAuthAttributes ofNaver(final String userNameAttributeName,
        final Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
            .name((String) response.get("name"))
            .email((String) response.get("email"))
            .picture((String) response.get("profile_image"))
            .attributes(response)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    private static OAuthAttributes ofKakao(final String userNameAttributeName,
        final Map<String, Object> attributes) {

        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        return OAuthAttributes.builder()
            .name((String) profile.get("nickname"))
            .email((String) account.get("email"))
            .picture((String) profile.get("profile_image_url"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    public Member toEntity() {
        return Member.builder()
            .name(name)
            .email(email)
            .picture(picture)
            .role(Role.GUEST)
            .job(Job.NO_JOB)
            .skills(Set.of(Skill.NO_SKILL))
            .build();
    }
}
