package me.lsh.javacrawler.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.controller.dto.CompetitionSearch;
import me.lsh.javacrawler.domain.event.competition.Competition;
import me.lsh.javacrawler.repository.event.competition.CompetitionRepository;
import me.lsh.javacrawler.service.dto.CompetitionListResponseDto;
import me.lsh.javacrawler.service.dto.CompetitionResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
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

    // 처음 실행 된 후 30초후 실행, 이후 이전에 실행된 task의 종료 시긴으로 부터 10분(600000) 후에 실행됨.
    @Scheduled(fixedDelay = 600000, initialDelay = 30000)
    @Transactional
    public void deleteAll() {
        List<Competition> competitions = repository.findAll();

        for (Competition competition : competitions) {
            if (competition.isExpired()) {
                log.info("만료된 공모전 삭제 -> {}", competition.getTitle());
                repository.delete(competition);
            }
        }
    }

    public CompetitionResponseDto findOne(final Long id) {
        Competition competition = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 공고가 없습니다, id = " + id));
        return new CompetitionResponseDto(competition);
    }
}
