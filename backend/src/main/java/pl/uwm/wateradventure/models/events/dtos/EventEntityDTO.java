package pl.uwm.wateradventure.models.events.dtos;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record EventEntityDTO(
                             Long id,
                             String type,
                             Double cost,
                             LocalDateTime date,
                             String city,
                             LocalTime duration,
                             Integer assignedParticipants,
                             Integer maxParticipantsNumber) {

    @Builder
    public EventEntityDTO {}

}
