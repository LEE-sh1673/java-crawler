package me.lsh.javacrawler.repository.event.competition;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import me.lsh.javacrawler.controller.dto.CompetitionSearch;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.domain.event.competition.QCompetition;
import me.lsh.javacrawler.service.dto.CompetitionListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionRepositoryImpl extends QuerydslRepositorySupport
    implements CompetitionCustomRepository {

    private final JPAQueryFactory queryFactory;

    private final EventCriteriaBuilder builder;

    public CompetitionRepositoryImpl(final JPAQueryFactory queryFactory,
        final EventCriteriaBuilder builder) {
        super(Competition.class);
        this.queryFactory = queryFactory;
        this.builder = builder;
    }

    @Override
    public Page<CompetitionListResponseDto> findAllByCriteria(final CompetitionSearch option,
        final Pageable pageable) {
        return findCompetitionPages(option, pageable);
    }

    private PageImpl<CompetitionListResponseDto> findCompetitionPages(
        final CompetitionSearch option,
        final Pageable pageable) {

        Predicate criteriaBuilder = builder.createCompetitionBuilder(option);
        OrderSpecifier<?> orderSpecifier = builder.getOrderSpecifier(option);

        return new PageImpl<>(
            findCompetitions(pageable, criteriaBuilder, orderSpecifier),
            pageable,
            getCount(criteriaBuilder)
        );
    }

    private List<CompetitionListResponseDto> findCompetitions(
        final Pageable pageable,
        final Predicate predicate,
        final OrderSpecifier<?> orderSpecifier) {

        List<Competition> competitions = queryFactory
            .selectFrom(QCompetition.competition)
            .where(predicate)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(orderSpecifier)
            .fetch();
        return mapCompetitions(competitions);
    }

    private List<CompetitionListResponseDto> mapCompetitions(final List<Competition> competitions) {
        return competitions.stream()
            .map(CompetitionListResponseDto::new)
            .collect(Collectors.toList());
    }

    private Long getCount(final Predicate predicate) {
        final QCompetition competition = QCompetition.competition;
        return queryFactory
            .select(competition.count())
            .from(competition)
            .where(predicate)
            .fetchOne();
    }
}
