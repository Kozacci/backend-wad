package pl.uwm.wateradventure.models.learning.general.dtos;

import lombok.Builder;

public record GeneralLearningEntityDTO(Long id,
                                       Integer questionsAnswered,
                                       Integer correctAnswers) {

    @Builder
    public GeneralLearningEntityDTO {}
}
