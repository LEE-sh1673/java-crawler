package me.lsh.javacrawler.repository.event.competition;

import me.lsh.javacrawler.controller.dto.CompetitionSearch;
import me.lsh.javacrawler.service.dto.CompetitionListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionCustomRepository {

    Page<CompetitionListResponseDto> findAllByCriteria(final CompetitionSearch option,
        final Pageable pageable);
}
