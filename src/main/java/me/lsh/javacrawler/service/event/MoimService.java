package me.lsh.javacrawler.service.event;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lsh.javacrawler.controller.event.dto.MoimSearch;
import me.lsh.javacrawler.controller.event.exception.EventNotFoundException;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.repository.event.moim.MoimRepository;
import me.lsh.javacrawler.service.dto.MoimListResponseDto;
import me.lsh.javacrawler.service.dto.MoimResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MoimService {

    private final MoimRepository repository;

    public Page<MoimListResponseDto> findCompetitions(
        final MoimSearch moimSearch,
        final Pageable pageable) {
        return repository.findAllByCriteria(moimSearch, pageable);
    }

    // 처음 실행 된 후 30초후 실행, 이후 이전에 실행된 task의 종료 시긴으로 부터 10분(600000) 후에 실행됨.
    @Scheduled(fixedDelay = 600000, initialDelay = 30000)
    @Transactional
    public void deleteAll() {
        List<Moim> moims = repository.findAll();

        for (Moim moim : moims) {
            if (moim.isExpired()) {
                log.info("만료된 모임 삭제 -> {}", moim.getTitle());
                repository.delete(moim);
            }
        }
    }

    public MoimResponseDto findOne(final Long id) {
        Moim moim = repository.findById(id)
            .orElseThrow(() -> new EventNotFoundException("해당 모임이 없습니다, id = " + id));
        return new MoimResponseDto(moim);
    }
}
