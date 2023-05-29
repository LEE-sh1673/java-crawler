package me.lsh.javacrawler.domain.event.competition;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompetition is a Querydsl query type for Competition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompetition extends EntityPathBase<Competition> {

    private static final long serialVersionUID = -1187467311L;

    public static final QCompetition competition = new QCompetition("competition");

    public final me.lsh.javacrawler.domain.event.QEvent _super = new me.lsh.javacrawler.domain.event.QEvent(this);

    //inherited
    public final SetPath<me.lsh.javacrawler.domain.event.ApplicantType, EnumPath<me.lsh.javacrawler.domain.event.ApplicantType>> applicants = _super.applicants;

    public final StringPath awardAmount = createString("awardAmount");

    public final SetPath<AwardBenefit, EnumPath<AwardBenefit>> awardBenefits = this.<AwardBenefit, EnumPath<AwardBenefit>>createSet("awardBenefits", AwardBenefit.class, EnumPath.class, PathInits.DIRECT2);

    public final EnumPath<AwardScale> awardScale = createEnum("awardScale", AwardScale.class);

    //inherited
    public final NumberPath<Integer> bookmarkCount = _super.bookmarkCount;

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final EnumPath<me.lsh.javacrawler.domain.event.EventType> eventType = _super.eventType;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> expiredAt = _super.expiredAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    //inherited
    public final StringPath organizer = _super.organizer;

    //inherited
    public final StringPath originalLink = _super.originalLink;

    public final NumberPath<Integer> pageId = createNumber("pageId", Integer.class);

    //inherited
    public final EnumPath<me.lsh.javacrawler.domain.event.EventProvider> provider = _super.provider;

    //inherited
    public final SetPath<me.lsh.javacrawler.domain.skill.Skill, EnumPath<me.lsh.javacrawler.domain.skill.Skill>> skills = _super.skills;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> startedAt = _super.startedAt;

    //inherited
    public final EnumPath<me.lsh.javacrawler.domain.event.RecruitmentStatus> status = _super.status;

    //inherited
    public final StringPath thumbnail = _super.thumbnail;

    //inherited
    public final StringPath title = _super.title;

    //inherited
    public final NumberPath<Integer> viewCount = _super.viewCount;

    public QCompetition(String variable) {
        super(Competition.class, forVariable(variable));
    }

    public QCompetition(Path<? extends Competition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompetition(PathMetadata metadata) {
        super(Competition.class, metadata);
    }

}

