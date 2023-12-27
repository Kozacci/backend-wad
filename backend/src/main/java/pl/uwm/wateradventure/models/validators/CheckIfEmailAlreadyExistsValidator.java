package pl.uwm.wateradventure.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import pl.uwm.wateradventure.services.participants.crud.ParticipantRepository;

@RequiredArgsConstructor
public class CheckIfEmailAlreadyExistsValidator implements ConstraintValidator<EmailAlreadyExists, String> {

    private final ParticipantRepository participantRepository;

    @Override
    public boolean isValid(String givenValue, ConstraintValidatorContext constraintValidatorContext) {
        return !participantRepository.existsByEmail(givenValue);
    }

}
