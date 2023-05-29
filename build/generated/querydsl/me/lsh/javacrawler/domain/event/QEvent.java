package me.lsh.javacrawler.domain.event;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = 1179597979L;

    public static final QEvent event = new QEvent("event");

    public final me.lsh.javacrawler.domain.QBaseTimeEntity _super = new me.lsh.javacrawler.domain.QBaseTimeEntity(this);

    public final SetPath<ApplicantType, EnumPath<ApplicantType>> applicants = this.<ApplicantType, EnumPath<ApplicantType>>createSet("applicants", ApplicantType.class, EnumPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> bookmarkCount = createNumber("bookmarkCount", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<EventType> eventType = createEnum("eventType", EventType.class);

    public final DateTimePath<java.time.LocalDateTime> expiredAt = createDateTime("expiredAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath organizer = createString("organizer");

    public final StringPath originalLink = createString("originalLink");

    public final EnumPath<EventProvider> provider = createEnum("provider", EventProvider.class);

    public final SetPath<me.lsh.javacrawler.domain.skill.Skill, EnumPath<me.lsh.javacrawler.domain.skill.Skill>> skills = this.<me.lsh.javacrawler.domain.skill.Skill, EnumPath<me.lsh.javacrawler.domain.skill.Skill>>createSet("skills", me.lsh.javacrawler.domain.skill.Skill.class, EnumPath.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> startedAt = createDateTime("startedAt", java.time.LocalDateTime.class);

    public final EnumPath<RecruitmentStatus> status = createEnum("status", RecruitmentStatus.class);

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

