package pl.uwm.wateradventure.models.participants.security.dtos;

import lombok.Builder;

public record ParticipantRegisterDTO(String firstName,
                                     String lastName,
                                     String email,
                                     String password,
                                     String phoneNumber) {

    @Builder
    public ParticipantRegisterDTO {};
}
