package pl.uwm.wateradventure.models.participant_events.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;

public record ParticipantEventEntityCreateDTO(@NotNull(message = "'Event id' must be filled.")
                                              Long eventId,
                                              @NotBlank(message = "'Email' field must be filled.")
                                              @Email(message = "Must be a valid email address.")
                                              @Size(max = 40, message = "Email must contain less than 40 signs.")
                                              String ordererEmail,
                                              @NotBlank(message = "'First name' field must be filled.")
                                              @Size(min = 3, max = 30, message = "First name can contain 3 to 40 characters only.")
                                              @Pattern(regexp = "[A-Z a-z]+")
                                              String ordererFirstName,
                                              @NotBlank(message = "'Last name' field must be filled.")
                                              @Size(min = 3, max = 40, message = "Last name can contain 3 to 40 characters only.")
                                              @Pattern(regexp = "[A-Z a-z]+")
                                              String ordererLastName,
                                              @NotBlank(message = "'Phone number' field must be filled.")
                                              @Size(min = 9, max = 9, message = "'Phone number' must contains 9 digits.")
                                              @Pattern(regexp = "[\\d]+", message = "'Phone number' field can contain only digits.")
                                              String ordererPhoneNumber,
                                              @NotNull(message = "'Participants number' field must be filled.")
                                              @Positive(message = "'Participants number' field must be higher than 0.")
                                              Integer participantsNumber) {

    @Builder public ParticipantEventEntityCreateDTO {}
}
