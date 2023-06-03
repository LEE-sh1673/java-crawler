package me.lsh.javacrawler.domain.recommend;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFrequentSkill is a Querydsl query type for FrequentSkill
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFrequentSkill extends EntityPathBase<FrequentSkill> {

    private static final long serialVersionUID = 1800489286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFrequentSkill frequentSkill = new QFrequentSkill("frequentSkill");

    public final me.lsh.javacrawler.common.domain.QBaseTimeEntity _super = new me.lsh.javacrawler.common.domain.QBaseTimeEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final QRecommendCompetition recommendCompetition;

    public final EnumPath<me.lsh.javacrawler.domain.skill.Skill> skill = createEnum("skill", me.lsh.javacrawler.domain.skill.Skill.class);

    public QFrequentSkill(String variable) {
        this(FrequentSkill.class, forVariable(variable), INITS);
    }

    public QFrequentSkill(Path<? extends FrequentSkill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFrequentSkill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFrequentSkill(PathMetadata metadata, PathInits inits) {
        this(FrequentSkill.class, metadata, inits);
    }

    public QFrequentSkill(Class<? extends FrequentSkill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recommendCompetition = inits.isInitialized("recommendCompetition") ? new QRecommendCompetition(forProperty("recommendCompetition"), inits.get("recommendCompetition")) : null;
    }

}

