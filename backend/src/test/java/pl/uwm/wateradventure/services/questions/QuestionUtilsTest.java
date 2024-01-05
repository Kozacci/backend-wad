package pl.uwm.wateradventure.services.questions;

import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.CorrectAnswer;
import pl.uwm.wateradventure.models.questions.QuestionEntity;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;

public class QuestionUtilsTest {

    public static QuestionEntity getQuestionEntity() {
        return new QuestionEntity(
                "Question content",
                Category.PODSTAWY_LOCJI,
                "First answer",
                "Second answer",
                "Third answer",
                CorrectAnswer.FIRST_ANSWER,
                "explanation",
                "img");
    }

    public static QuestionCreateUpdateDTO getQuestionCreateUpdateDTO() {
        return new QuestionCreateUpdateDTO(
                "Question content",
                Category.PODSTAWY_LOCJI.enumValue,
                "First answer",
                "Second answer",
                "Third answer",
                "Odpowiedź A",
                "explanation",
                "img"
        );
    }
}
