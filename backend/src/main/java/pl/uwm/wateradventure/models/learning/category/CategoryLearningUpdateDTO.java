package pl.uwm.wateradventure.models.learning.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import pl.uwm.wateradventure.models.validators.Category;

public record CategoryLearningUpdateDTO(@NotNull
                                        Boolean isCorrectAnswer,
                                        @NotNull(message = "'Category' field must be filled.")
                                        @Category
                                        String category) {
}
