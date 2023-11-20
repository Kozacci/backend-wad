package pl.uwm.wateradventure.models.learning.general.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record GeneralLearningUpdateDTO(@NotNull(message = "Pole 'udzielonych odpowiedzi' nie może być puste.")
                                       @PositiveOrZero(message = "Pole 'udzielonych odpowiedzi' musi być większe od zera.")
                                       Integer questionsAnswered,
                                       @NotNull(message = "Pole 'poprawnych odpowiedzi' nie może być puste.")
                                       @PositiveOrZero(message = "Pole 'poprawnych odpowiedzi' musi być większe od zera.")
                                       Integer correctAnswers) {
}
