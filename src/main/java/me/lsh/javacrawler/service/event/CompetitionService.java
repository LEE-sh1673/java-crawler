package me.lsh.javacrawler.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.controller.event.dto.CompetitionSearch;
import me.lsh.javacrawler.controller.event.exception.EventNotFoundException;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.repository.event.competition.CompetitionRepository;
import me.lsh.javacrawler.service.dto.CompetitionListResponseDto;
import me.lsh.javacrawler.service.dto.CompetitionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository repository;

    public Page<CompetitionListResponseDto> findAllByCriteria(
        final CompetitionSearch competitionSearch,
        final Pageable pageable) {
        return repository.findAllByCriteria(competitionSearch, pageable);
    }

    public CompetitionResponseDto findOne(final Long id) {
        Competition competition = repository.findById(id)
            .orElseThrow(() -> new EventNotFoundException("해당 공고가 없습니다, id = " + id));
        return new CompetitionResponseDto(competition);
    }
}
