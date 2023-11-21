package pl.uwm.wateradventure.models.learning.general.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record GeneralLearningUpdateDTO(@NotNull(message = "'Questions answered' field must be filled.")
                                       @PositiveOrZero(message = "'Questions answered' field must be higher than zero.")
                                       Integer questionsAnswered,
                                       @NotNull(message = "'Correct answers' field must be filled.")
                                       @PositiveOrZero(message = "'Correct answers' field must be higher than zero.")
                                       Integer correctAnswers) {
}
