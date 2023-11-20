package pl.uwm.wateradventure.services.questions.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.VarLengthExceededException;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.CorrectAnswer;
import pl.uwm.wateradventure.models.questions.QuestionEntity;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;

@Component
@RequiredArgsConstructor
public class QuestionUpdater {

    private final QuestionRepository questionRepository;

    public QuestionEntityDTO updateQuestion(QuestionEntity questionToUpdate, QuestionCreateUpdateDTO questionUpdateDTO) {
        checkContent(questionToUpdate, questionUpdateDTO.content());
        checkCategory(questionToUpdate, questionUpdateDTO.category());
        checkAnswer(questionToUpdate, questionUpdateDTO.firstAnswer(), 1);
        checkAnswer(questionToUpdate, questionUpdateDTO.secondAnswer(), 2);
        checkAnswer(questionToUpdate, questionUpdateDTO.thirdAnswer(), 3);
        checkCorrectAnswer(questionToUpdate, questionUpdateDTO.correctAnswer());
        checkExplanation(questionToUpdate, questionUpdateDTO.explanation());
        checkImage(questionToUpdate, questionUpdateDTO.image());
        questionRepository.saveAndFlush(questionToUpdate);
        return questionToUpdate.toDTO();
    }

    private void checkContent(QuestionEntity question, String content) {
        if (content == null) return;

        question.setContent(content);
    }

    private void checkCategory(QuestionEntity question, String category) {
        if (category == null) return;

        var newQuestionCategory = Category.getCategory(category);
        question.setCategory(newQuestionCategory);
    }

    private void checkAnswer(QuestionEntity question, String answer, Integer answerNumber) {
        if (answer == null) return;

        if (answer.length() > 60) {
            throw new VarLengthExceededException("Answer " + answerNumber, "The answer cannot exceed 60 characters.");
        }
        if (answerNumber == 1) {
            question.setFirstAnswer(answer);
            return;
        }
        if (answerNumber == 2) {
            question.setSecondAnswer(answer);
            return;
        }
        if (answerNumber == 3) {
            question.setThirdAnswer(answer);
        }
    }

    private void checkCorrectAnswer(QuestionEntity question, String correctAnswer) {
        if (correctAnswer == null) return;

        var newCorrectAnswer = CorrectAnswer.getCorrectAnswer(correctAnswer);
        question.setCorrectAnswer(newCorrectAnswer);
    }

    private void checkExplanation(QuestionEntity question, String explanation) {
        if (explanation == null) return;

        question.setExplanation(explanation);
    }

    private void checkImage(QuestionEntity question, String image) {
        if (image == null) return;

        question.setImage(image);
    }

}
