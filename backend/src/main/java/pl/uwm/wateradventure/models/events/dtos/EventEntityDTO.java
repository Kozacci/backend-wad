package pl.uwm.wateradventure.models.events.dtos;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record EventEntityDTO(String type,
                             Double cost,
                             LocalDateTime date,
                             String city,
                             LocalTime duration,
                             Integer maxParticipantsNumber) {

    @Builder
    public EventEntityDTO {}

}
