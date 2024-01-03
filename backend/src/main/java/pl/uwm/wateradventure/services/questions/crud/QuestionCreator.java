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

    public QuestionEntityDTO addQuestion(QuestionCreateUpdateDTO questionCreateDTO) {
        // TODO: dodać test sprawdzajacy czy dodaje sie czy nie xd
        var questionEntity = repository.saveAndFlush(
                new QuestionEntity(
                    questionCreateDTO.content(),
                    Category.getCategory(questionCreateDTO.category()),
                    questionCreateDTO.firstAnswer(),
                    questionCreateDTO.secondAnswer(),
                    questionCreateDTO.thirdAnswer(),
                    CorrectAnswer.getCorrectAnswer(questionCreateDTO.correctAnswer()),
                    questionCreateDTO.explanation(),
                    questionCreateDTO.image())
        );
        return questionEntity.toDTO();
    }
}
