package pl.uwm.wateradventure.models.learning.general.dtos;

import lombok.Builder;

public record GeneralLearningDTO(Long id,
                                 Integer questionsAnswered,
                                 Integer correctAnswers) {

    @Builder
    public GeneralLearningDTO {}
}
