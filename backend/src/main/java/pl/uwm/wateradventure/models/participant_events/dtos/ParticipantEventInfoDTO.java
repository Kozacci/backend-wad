package pl.uwm.wateradventure.models.participant_events.dtos;

import lombok.Builder;

public record ParticipantEventInfoDTO(Long participantEventId,
                                      String ordererEmail,
                                      String ordererFirstName,
                                      String ordererLastName,
                                      String ordererPhoneNumber,
                                      Integer participantsNumber,
                                      Boolean isPaid) {

    public @Builder ParticipantEventInfoDTO {}
}
