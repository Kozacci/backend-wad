package pl.uwm.wateradventure.models.learning.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import pl.uwm.wateradventure.models.validators.Category;

public record CategoryLearningUpdateDTO(
                                        @NotNull
                                        @PositiveOrZero
                                        Integer questionsAnswered,
                                        @NotNull
                                        @PositiveOrZero
                                        Integer correctAnswers,
                                        @NotNull
                                        @Category
                                        String category) {
}
