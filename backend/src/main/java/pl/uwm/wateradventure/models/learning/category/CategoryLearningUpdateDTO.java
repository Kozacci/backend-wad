package pl.uwm.wateradventure.models.learning.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import pl.uwm.wateradventure.models.validators.Category;

public record CategoryLearningUpdateDTO(
                                        @NotNull(message = "Pole 'udzielonych odpowiedzi' nie może być puste.")
                                        @PositiveOrZero(message = "Pole 'udzielonych odpowiedzi' musi być większe od zera.")
                                        Integer questionsAnswered,
                                        @NotNull(message = "Pole 'poprawnych odpowiedzi' nie może być puste.")
                                        @PositiveOrZero(message = "Pole 'poprawnych odpowiedzi' musi być większe od zera.")
                                        Integer correctAnswers,
                                        @NotNull(message = "Pole 'kategoria' nie może być puste.")
                                        @Category
                                        String category) {
}
