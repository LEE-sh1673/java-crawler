package me.lsh.javacrawler.domain.recommend;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecommendCompetition is a Querydsl query type for RecommendCompetition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendCompetition extends EntityPathBase<RecommendCompetition> {

    private static final long serialVersionUID = 1445588192L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecommendCompetition recommendCompetition = new QRecommendCompetition("recommendCompetition");

    public final me.lsh.javacrawler.common.domain.QBaseTimeEntity _super = new me.lsh.javacrawler.common.domain.QBaseTimeEntity(this);

    public final ListPath<FrequentCompetitionAttribute, QFrequentCompetitionAttribute> attributes = this.<FrequentCompetitionAttribute, QFrequentCompetitionAttribute>createList("attributes", FrequentCompetitionAttribute.class, QFrequentCompetitionAttribute.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final me.lsh.javacrawler.domain.member.QMember member;

    public final ListPath<FrequentSkill, QFrequentSkill> skills = this.<FrequentSkill, QFrequentSkill>createList("skills", FrequentSkill.class, QFrequentSkill.class, PathInits.DIRECT2);

    public QRecommendCompetition(String variable) {
        this(RecommendCompetition.class, forVariable(variable), INITS);
    }

    public QRecommendCompetition(Path<? extends RecommendCompetition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecommendCompetition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecommendCompetition(PathMetadata metadata, PathInits inits) {
        this(RecommendCompetition.class, metadata, inits);
    }

    public QRecommendCompetition(Class<? extends RecommendCompetition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new me.lsh.javacrawler.domain.member.QMember(forProperty("member")) : null;
    }

}

