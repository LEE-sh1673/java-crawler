package me.lsh.javacrawler.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -174473789L;

    public static final QMember member = new QMember("member1");

    public final me.lsh.javacrawler.domain.QBaseTimeEntity _super = new me.lsh.javacrawler.domain.QBaseTimeEntity(this);

    public final ListPath<me.lsh.javacrawler.domain.event.BookmarkEvent, me.lsh.javacrawler.domain.event.QBookmarkEvent> bookmarkEvents = this.<me.lsh.javacrawler.domain.event.BookmarkEvent, me.lsh.javacrawler.domain.event.QBookmarkEvent>createList("bookmarkEvents", me.lsh.javacrawler.domain.event.BookmarkEvent.class, me.lsh.javacrawler.domain.event.QBookmarkEvent.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<Job> job = createEnum("job", Job.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath name = createString("name");

    public final StringPath picture = createString("picture");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final SetPath<me.lsh.javacrawler.domain.skill.Skill, EnumPath<me.lsh.javacrawler.domain.skill.Skill>> skills = this.<me.lsh.javacrawler.domain.skill.Skill, EnumPath<me.lsh.javacrawler.domain.skill.Skill>>createSet("skills", me.lsh.javacrawler.domain.skill.Skill.class, EnumPath.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

