package pl.uwm.wateradventure.models.questions.dtos;

import pl.uwm.wateradventure.models.learning.category.Category;

public record QuestionFiltersDTO(Long id,
                                 String content,
                                 Category category,
                                 String sortBy) {
}
