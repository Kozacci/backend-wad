package pl.uwm.wateradventure.models.learning.general.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record GeneralLearningUpdateDTO(@NotNull
                                       @PositiveOrZero
                                       Integer questionsAnswered,
                                       @NotNull
                                       @PositiveOrZero
                                       Integer correctAnswers) {
}
