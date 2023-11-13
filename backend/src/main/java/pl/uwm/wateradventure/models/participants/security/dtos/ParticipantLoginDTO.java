package pl.uwm.wateradventure.models.participants.security.dtos;

import lombok.Builder;

public record ParticipantLoginDTO(String email,
                                  String password) {

    @Builder
    public ParticipantLoginDTO {};
}
