package pl.uwm.wateradventure.models.participants.security.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pl.uwm.wateradventure.models.validators.EmailAlreadyExists;

public record ParticipantRegisterDTO(@NotBlank(message = "'First name' field must be filled.")
                                     @Size(min = 3, max = 30, message = "First name can contain 3 to 40 characters only.")
                                     @Pattern(regexp = "[A-Z a-z]+")
                                     String firstName,
                                     @NotBlank(message = "'Last name' field must be filled.")
                                     @Size(min = 3, max = 40, message = "Last name can contain 3 to 40 characters only.")
                                     @Pattern(regexp = "[A-Z a-z]+")
                                     String lastName,
                                     @NotBlank(message = "'Email' field must be filled.")
                                     @Email(message = "Must be a valid email address.")
                                     @Size(max = 40, message = "Email must contain less than 40 signs.")
                                     @EmailAlreadyExists
                                     String email,
                                     @NotBlank(message = "'Password' field must be filled.")
                                     @Size(max = 30, message = "Password must contain less than 30 signs.")
                                     String password,
                                     @NotBlank(message = "'Phone number' field must be filled.")
                                     @Size(min = 9, max = 9, message = "'Phone number' must contains 9 digits.")
                                     @Pattern(regexp = "[\\d]+", message = "'Phone number' field can contain only digits.")
                                     String phoneNumber) {

    @Builder
    public ParticipantRegisterDTO {}
}
