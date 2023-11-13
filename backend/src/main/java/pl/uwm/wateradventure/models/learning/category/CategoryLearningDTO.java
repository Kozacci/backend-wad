package pl.uwm.wateradventure.models.learning.category;

import lombok.Builder;

public record CategoryLearningDTO(Long id,
                                  Integer questionsAnswered,
                                  Integer correctAnswers,
                                  String category
                                  ) {

    @Builder
    public CategoryLearningDTO {};
}
