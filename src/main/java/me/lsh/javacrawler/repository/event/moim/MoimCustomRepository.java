package me.lsh.javacrawler.repository.event.moim;

import me.lsh.javacrawler.controller.dto.MoimSearch;
import me.lsh.javacrawler.service.dto.MoimListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MoimCustomRepository {

    Page<MoimListResponseDto> findAllByCriteria(final MoimSearch option, final Pageable pageable);
}
