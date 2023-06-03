package me.lsh.javacrawler.domain.bookmark;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookmarkEvent is a Querydsl query type for BookmarkEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmarkEvent extends EntityPathBase<BookmarkEvent> {

    private static final long serialVersionUID = 1175717527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookmarkEvent bookmarkEvent = new QBookmarkEvent("bookmarkEvent");

    public final me.lsh.javacrawler.common.domain.QBaseTimeEntity _super = new me.lsh.javacrawler.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final me.lsh.javacrawler.domain.event.QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final me.lsh.javacrawler.domain.member.QMember member;

    public QBookmarkEvent(String variable) {
        this(BookmarkEvent.class, forVariable(variable), INITS);
    }

    public QBookmarkEvent(Path<? extends BookmarkEvent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookmarkEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookmarkEvent(PathMetadata metadata, PathInits inits) {
        this(BookmarkEvent.class, metadata, inits);
    }

    public QBookmarkEvent(Class<? extends BookmarkEvent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new me.lsh.javacrawler.domain.event.QEvent(forProperty("event")) : null;
        this.member = inits.isInitialized("member") ? new me.lsh.javacrawler.domain.member.QMember(forProperty("member")) : null;
    }

}

