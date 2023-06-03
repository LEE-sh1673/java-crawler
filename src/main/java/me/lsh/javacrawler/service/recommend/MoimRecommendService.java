package me.lsh.javacrawler.service.recommend;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.lsh.javacrawler.repository.event.moim.MoimRepository;
import me.lsh.javacrawler.repository.recommend.MoimRecommendRepository;
import me.lsh.javacrawler.service.bookmark.BookmarkService;
import me.lsh.javacrawler.service.dto.MoimResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MoimRecommendService {

    private final MoimRecommendRepository moimRecommendRepository;

    private final MoimRepository moimRepository;

    private final BookmarkService bookmarkService;

    public Page<MoimResponseDto> recommendAllBySkill(
        final long memberId, final Pageable pageable) {
        List<Long> moimIds = moimRecommendRepository
            .findAllByMostFrequentSkills(memberId)
            .stream()
            .filter((moimId) -> !bookmarkService.isBookmarked(memberId, moimId))
            .collect(Collectors.toList());

        return moimRepository.findAllByIds(moimIds, pageable)
            .map(MoimResponseDto::new);
    }

//    public List<MostFrequentSkillDto> findMostFrequentSkills(final long memberId) {
//        return moimRecommendRepository
//            .findMostFrequentSkills(memberId)
//            .stream()
//            .map(MostFrequentSkillDto::new)
//            .collect(Collectors.toList());
//    }
}
