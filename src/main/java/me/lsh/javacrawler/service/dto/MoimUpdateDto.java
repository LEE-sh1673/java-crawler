package me.lsh.javacrawler.service.dto;

import lombok.Getter;
import me.lsh.javacrawler.domain.event.moim.Moim;
import me.lsh.javacrawler.domain.event.moim.MoimType;

@Getter
public class MoimUpdateDto extends EventUpdateDto {

    private String location;

    private MoimType moimType;

    private String duration;

    private boolean feeRequired;

    public MoimUpdateDto(final Moim moim) {
        super(moim);
        this.location = moim.getLocation();
        this.moimType = moim.getMoimType();
        this.duration = moim.getDuration();
        this.feeRequired = moim.isFeeRequired();
    }
}
