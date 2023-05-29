package me.lsh.javacrawler.domain.event.moim;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMoim is a Querydsl query type for Moim
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMoim extends EntityPathBase<Moim> {

    private static final long serialVersionUID = -24057169L;

    public static final QMoim moim = new QMoim("moim");

    public final me.lsh.javacrawler.domain.event.QEvent _super = new me.lsh.javacrawler.domain.event.QEvent(this);

    //inherited
    public final SetPath<me.lsh.javacrawler.domain.event.ApplicantType, EnumPath<me.lsh.javacrawler.domain.event.ApplicantType>> applicants = _super.applicants;

    //inherited
    public final NumberPath<Integer> bookmarkCount = _super.bookmarkCount;

    //inherited
    public final StringPath content = _super.content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath duration = createString("duration");

    //inherited
    public final EnumPath<me.lsh.javacrawler.domain.event.EventType> eventType = _super.eventType;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> expiredAt = _super.expiredAt;

    public final BooleanPath feeRequired = createBoolean("feeRequired");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath location = createString("location");

    public final EnumPath<MoimType> moimType = createEnum("moimType", MoimType.class);

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

    public QMoim(String variable) {
        super(Moim.class, forVariable(variable));
    }

    public QMoim(Path<? extends Moim> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMoim(PathMetadata metadata) {
        super(Moim.class, metadata);
    }

}

