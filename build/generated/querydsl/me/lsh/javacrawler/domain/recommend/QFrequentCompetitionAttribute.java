package me.lsh.javacrawler.domain.recommend;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFrequentCompetitionAttribute is a Querydsl query type for FrequentCompetitionAttribute
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFrequentCompetitionAttribute extends EntityPathBase<FrequentCompetitionAttribute> {

    private static final long serialVersionUID = 717608968L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFrequentCompetitionAttribute frequentCompetitionAttribute = new QFrequentCompetitionAttribute("frequentCompetitionAttribute");

    public final me.lsh.javacrawler.common.domain.QBaseTimeEntity _super = new me.lsh.javacrawler.common.domain.QBaseTimeEntity(this);

    public final EnumPath<me.lsh.javacrawler.domain.event.ApplicantType> applicant = createEnum("applicant", me.lsh.javacrawler.domain.event.ApplicantType.class);

    public final EnumPath<me.lsh.javacrawler.domain.event.competition.AwardBenefit> awardBenefit = createEnum("awardBenefit", me.lsh.javacrawler.domain.event.competition.AwardBenefit.class);

    public final EnumPath<me.lsh.javacrawler.domain.event.competition.AwardScale> awardScale = createEnum("awardScale", me.lsh.javacrawler.domain.event.competition.AwardScale.class);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final QRecommendCompetition recommendCompetition;

    public QFrequentCompetitionAttribute(String variable) {
        this(FrequentCompetitionAttribute.class, forVariable(variable), INITS);
    }

    public QFrequentCompetitionAttribute(Path<? extends FrequentCompetitionAttribute> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFrequentCompetitionAttribute(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFrequentCompetitionAttribute(PathMetadata metadata, PathInits inits) {
        this(FrequentCompetitionAttribute.class, metadata, inits);
    }

    public QFrequentCompetitionAttribute(Class<? extends FrequentCompetitionAttribute> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recommendCompetition = inits.isInitialized("recommendCompetition") ? new QRecommendCompetition(forProperty("recommendCompetition"), inits.get("recommendCompetition")) : null;
    }

}

