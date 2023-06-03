package me.lsh.javacrawler.domain.recommend;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.lsh.javacrawler.common.domain.BaseTimeEntity;
import me.lsh.javacrawler.domain.member.Member;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendCompetition extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_competition_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "recommendCompetition", cascade = CascadeType.REMOVE)
    private List<FrequentCompetitionAttribute> attributes = new ArrayList<>();

    @OneToMany(mappedBy = "recommendCompetition", cascade = CascadeType.REMOVE)
    private List<FrequentSkill> skills = new ArrayList<>();

    public static RecommendCompetition createRecommendCompetition(final Member member,
        final List<FrequentCompetitionAttribute> attributes, final List<FrequentSkill> skills) {
        RecommendCompetition recommendCompetition = new RecommendCompetition();
        recommendCompetition.setMember(member);
        recommendCompetition.setAttributes(attributes);
        recommendCompetition.setSkills(skills);
        return recommendCompetition;
    }

    private void setMember(final Member member) {
        this.member = member;
    }

    private void setAttributes(final List<FrequentCompetitionAttribute> attributes) {
        this.attributes = attributes;
        for (FrequentCompetitionAttribute attribute : attributes) {
            attribute.setRecommendCompetition(this);
        }
    }

    private void setSkills(final List<FrequentSkill> attributes) {
        this.skills = attributes;
        for (FrequentSkill attribute : attributes) {
            attribute.setRecommendCompetition(this);
        }
    }
}
