package me.lsh.javacrawler.repository.event.competition;

import static java.time.DayOfWeek.MONDAY;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import java.time.LocalDateTime;
import me.lsh.javacrawler.controller.dto.CompetitionSearch;
import me.lsh.javacrawler.controller.dto.MoimSearch;
import me.lsh.javacrawler.domain.event.QEvent;
import me.lsh.javacrawler.domain.event.competition.QCompetition;
import me.lsh.javacrawler.domain.event.moim.QMoim;
import me.lsh.javacrawler.repository.event.DateOption;
import org.springframework.stereotype.Component;

@Component
public class EventCriteriaBuilder {

    public Predicate createCompetitionBuilder(final CompetitionSearch option) {
        final QCompetition competition = QCompetition.competition;
        final BooleanBuilder builder = new BooleanBuilder();

        if (option.hasName()) {
            builder.and(competition.title.containsIgnoreCase(option.getName()));
        }
        if (option.hasDateOption()) {
            setBuilderByDateOption(builder, competition._super, option.getDateOption());
        }
        if (option.hasApplicantType()) {
            builder.and(competition.applicants.any().in(option.getApplicantTypes()));
        }
        if (option.hasSkills()) {
            builder.and(competition.skills.any().in(option.getSkills()));
        }
        if (option.hasAwardScale()) {
            builder.and(competition.awardScale.eq(option.getAwardScale()));
        }
        if (option.hasAwardBenefit()) {
            builder.and(competition.awardBenefits.any().in(option.getAwardBenefits()));
        }
        return builder;
    }

    public Predicate createMoimBuilder(final MoimSearch option) {
        final QMoim moim = QMoim.moim;
        final BooleanBuilder builder = new BooleanBuilder();

        if (option.hasName()) {
            builder.and(moim.title.containsIgnoreCase(option.getName()));
        }
        if (option.hasDateOption()) {
            setBuilderByDateOption(builder, moim._super, option.getDateOption());
        }
        if (option.hasApplicantType()) {
            builder.and(moim.applicants.any().in(option.getApplicantTypes()));
        }
        if (option.hasSkills()) {
            builder.and(moim.skills.any().in(option.getSkills()));
        }
        if (option.hasMoimType()) {
            builder.and(moim.moimType.eq(option.getMoimType()));
        }
        return builder;
    }

    private void setBuilderByDateOption(final BooleanBuilder builder,
        final QEvent event,
        final DateOption option) {

        final LocalDateTime currentDate = LocalDateTime.now();

        switch (option) {
            case ALL:
                break;
            case TODAY:
                builder
                    .and(event.startedAt.loe(currentDate))
                    .and(event.expiredAt.goe(currentDate))
                    .and(event.startedAt.before(currentDate.plusDays(1)));
                break;
            case THIS_WEEK:
                LocalDateTime startOfWeek = currentDate
                    .with(MONDAY)
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0);
                LocalDateTime endOfWeek = startOfWeek.plusDays(7);
                builder
                    .and(event.startedAt.loe(endOfWeek))
                    .and(event.expiredAt.goe(startOfWeek));
                break;
            case THIS_MONTH:
                LocalDateTime startOfMonth = currentDate
                    .withDayOfMonth(1)
                    .withHour(0)
                    .withMinute(0)
                    .withSecond(0);
                LocalDateTime endOfMonth = startOfMonth
                    .plusMonths(1)
                    .minusNanos(1);
                builder
                    .and(event.startedAt.loe(endOfMonth))
                    .and(event.expiredAt.goe(startOfMonth));
                break;
            case CLOSED:
                builder.and(event.expiredAt.before(currentDate));
                break;
        }
    }

    public OrderSpecifier<?> getOrderSpecifier(final CompetitionSearch option) {
        if (!option.hasSort()) {
            return QCompetition.competition.pageId.desc();
        }
        switch (option.getSort()) {
            case "title":
                return QCompetition.competition.title.desc();
            case "bookmark":
                return QCompetition.competition.bookmarkCount.desc();
            default:
                return QCompetition.competition.pageId.desc();
        }
    }

    public OrderSpecifier<?> getMoimOrderSpecifier(final MoimSearch option) {
        if (!option.hasSort()) {
            return QMoim.moim.pageId.desc();
        }
        switch (option.getSort()) {
            case "title":
                return QMoim.moim.title.desc();
            case "bookmark":
                return QMoim.moim.bookmarkCount.desc();
            default:
                return QMoim.moim.pageId.desc();
        }
    }
}
