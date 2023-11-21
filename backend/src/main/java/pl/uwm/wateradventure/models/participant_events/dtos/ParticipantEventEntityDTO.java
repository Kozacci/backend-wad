package pl.uwm.wateradventure.models.participant_events.dtos;

import lombok.Builder;

import java.time.LocalDateTime;
import java.time.LocalTime;


public record ParticipantEventEntityDTO(Long participantEventId,
                                        String ordererEmail,
                                        String ordererFirstName,
                                        String ordererLastName,
                                        String ordererPhoneNumber,
                                        Integer participantsNumber,
                                        Boolean isPaid,
                                        LocalDateTime eventDate,
                                        String eventCity,
                                        LocalTime eventDuration,
                                        Integer eventMaxParticipantsNumber,
                                        Integer assignedParticipantsNumber) {

    @Builder
    public ParticipantEventEntityDTO {}
}
