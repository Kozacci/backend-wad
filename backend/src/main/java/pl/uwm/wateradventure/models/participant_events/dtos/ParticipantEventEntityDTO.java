package pl.uwm.wateradventure.models.participant_events.dtos;

import lombok.Builder;


public record ParticipantEventEntityDTO(String ordererEmail,
                                        String ordererFirstName,
                                        String ordererLastName,
                                        String ordererPhoneNumber,
                                        Integer participantsNumber,
                                        Boolean isPaid) {

    @Builder
    public ParticipantEventEntityDTO {}
}
