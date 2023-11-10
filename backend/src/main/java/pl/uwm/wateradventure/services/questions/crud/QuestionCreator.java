package pl.uwm.wateradventure.services.questions.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.CorrectAnswer;
import pl.uwm.wateradventure.models.questions.QuestionEntity;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;

@Component
@RequiredArgsConstructor
class QuestionCreator {

    private final QuestionRepository repository;

    public QuestionEntityDTO addQuestion(QuestionCreateUpdateDTO dto) {
        var questionEntity = repository.saveAndFlush(
                new QuestionEntity(
                    dto.content(), Category.getCategory(dto.category()),
                    dto.firstAnswer(), dto.secondAnswer(),
                    dto.thirdAnswer(), CorrectAnswer.getCorrectAnswer(dto.correctAnswer()),
                    dto.explanation(), dto.image())
        );
        return questionEntity.toDTO();
    }
}
