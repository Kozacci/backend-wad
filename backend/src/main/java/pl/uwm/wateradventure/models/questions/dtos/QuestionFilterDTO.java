package pl.uwm.wateradventure.models.questions.dtos;

import pl.uwm.wateradventure.models.learning.category.Category;

public record QuestionFilterDTO(Long id,
                                String content,
                                Category category,
                                String firstAnswer,
                                String secondAnswer,
                                String thirdAnswer) {
}
