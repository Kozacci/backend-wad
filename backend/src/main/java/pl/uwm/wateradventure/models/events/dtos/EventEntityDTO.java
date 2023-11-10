package pl.uwm.wateradventure.models.events.dtos;

import lombok.Builder;

import java.time.LocalTime;
import java.util.Date;

public record EventEntityDTO(String type,
                             Double cost,
                             Date date,
                             String city,
                             LocalTime duration,
                             Integer maxParticipantsNumber) {

    @Builder
    public EventEntityDTO {}

}
