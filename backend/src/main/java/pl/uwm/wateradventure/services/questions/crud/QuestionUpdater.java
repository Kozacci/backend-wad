package pl.uwm.wateradventure.services.questions.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.CorrectAnswer;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;

@Component
@RequiredArgsConstructor
public class QuestionUpdater {

    private final QuestionRepository questionRepository;
    private final QuestionReader questionReader;

    public QuestionEntityDTO updateQuestion(Long questionId, QuestionCreateUpdateDTO questionUpdateDTO) {
        var questionToUpdate = questionReader.getQuestionById(questionId);
        questionToUpdate.setContent(questionUpdateDTO.content());
        questionToUpdate.setCategory(Category.getCategory(questionUpdateDTO.category()));
        questionToUpdate.setFirstAnswer(questionUpdateDTO.firstAnswer());
        questionToUpdate.setSecondAnswer(questionUpdateDTO.secondAnswer());
        questionToUpdate.setThirdAnswer(questionUpdateDTO.thirdAnswer());
        questionToUpdate.setCorrectAnswer(CorrectAnswer.getCorrectAnswer(questionUpdateDTO.correctAnswer()));
        questionToUpdate.setExplanation(questionUpdateDTO.explanation());
        questionToUpdate.setImage(questionUpdateDTO.image());
        questionRepository.saveAndFlush(questionToUpdate);
        return questionToUpdate.toDTO();
    }
}
