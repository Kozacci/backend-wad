package pl.uwm.wateradventure.models.participants.security.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record ParticipantLoginDTO(@NotBlank(message = "'Email' field must be filled.")
                                  @Size(max = 40, message = "Email must contain less than 40 signs.")
                                  String email,
                                  @NotBlank(message = "'Password' field must be filled.")
                                  @Size(max = 30, message = "Password must contain less than 30 signs.")
                                  String password) {

    @Builder
    public ParticipantLoginDTO {};
}
