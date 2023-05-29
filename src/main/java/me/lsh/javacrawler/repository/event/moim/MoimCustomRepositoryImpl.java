package me.lsh.javacrawler.repository.event.moim;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import me.lsh.javacrawler.controller.dto.MoimSearch;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.event.moim.QMoim;
import me.lsh.javacrawler.repository.event.competition.EventCriteriaBuilder;
import me.lsh.javacrawler.service.dto.MoimListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MoimCustomRepositoryImpl extends QuerydslRepositorySupport
    implements MoimCustomRepository {

    private final JPAQueryFactory queryFactory;

    private final EventCriteriaBuilder builder;

    public MoimCustomRepositoryImpl(final JPAQueryFactory queryFactory,
        final EventCriteriaBuilder builder) {
        super(Moim.class);
        this.queryFactory = queryFactory;
        this.builder = builder;
    }

    @Override
    public Page<MoimListResponseDto> findAllByCriteria(final MoimSearch option,
        final Pageable pageable) {
        return findMoimPages(option, pageable);
    }

    private Page<MoimListResponseDto> findMoimPages(
        final MoimSearch option,
        final Pageable pageable) {

        Predicate predicate = builder.createMoimBuilder(option);
        OrderSpecifier<?> orderSpecifier = builder.getMoimOrderSpecifier(option);

        return new PageImpl<>(
            findMoims(pageable, predicate, orderSpecifier),
            pageable,
            getCount(predicate)
        );
    }

    private List<MoimListResponseDto> findMoims(
        final Pageable pageable,
        final Predicate predicate,
        final OrderSpecifier<?> orderSpecifier) {

        List<Moim> moims = queryFactory
            .selectFrom(QMoim.moim)
            .where(predicate)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(orderSpecifier)
            .fetch();
        return mapMoims(moims);
    }

    private List<MoimListResponseDto> mapMoims(final List<Moim> moims) {
        return moims.stream()
            .map(MoimListResponseDto::new)
            .collect(Collectors.toList());
    }

    private Long getCount(final Predicate predicate) {
        final QMoim moim = QMoim.moim;
        return queryFactory
            .select(moim.count())
            .from(moim)
            .where(predicate)
            .fetchOne();
    }
}
