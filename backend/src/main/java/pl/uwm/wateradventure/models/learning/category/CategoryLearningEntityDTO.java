package pl.uwm.wateradventure.models.learning.category;

import lombok.Builder;

public record CategoryLearningEntityDTO(Long id,
                                        Integer questionsAnswered,
                                        Integer correctAnswers,
                                        String category
                                  ) {

    @Builder
    public CategoryLearningEntityDTO {}
}
