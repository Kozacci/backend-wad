package pl.uwm.wateradventure.models.learning.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import pl.uwm.wateradventure.models.validators.Category;

public record CategoryLearningUpdateDTO(
                                        @NotNull(message = "'Questions answered' field must be filled.")
                                        @PositiveOrZero(message = "'Questions answered' field must be higher than zero.")
                                        Integer questionsAnswered,
                                        @NotNull(message = "'Correct answers' field must be filled.")
                                        @PositiveOrZero(message = "'Correct answers' field must be higher than zero.")
                                        Integer correctAnswers,
                                        @NotNull(message = "'Category' field must be filled.")
                                        @Category
                                        String category) {
}
