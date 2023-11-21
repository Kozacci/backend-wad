package pl.uwm.wateradventure.models.questions.dtos;

import lombok.Builder;

public record QuestionEntityDTO(Long questionId,
                                String content,
                                String category,
                                String firstAnswer,
                                String secondAnswer,
                                String thirdAnswer,
                                String correctAnswer,
                                String explanation,
                                String image
                                ) {

    @Builder
    public QuestionEntityDTO {}

}
