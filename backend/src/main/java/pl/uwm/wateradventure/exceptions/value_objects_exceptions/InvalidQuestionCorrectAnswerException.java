package pl.uwm.wateradventure.exceptions.value_objects_exceptions;

import pl.uwm.wateradventure.models.questions.CorrectAnswer;

public class InvalidQuestionCorrectAnswerException extends RuntimeException {

    public static String MESSAGE = String.format("Correct answer must be one of '%s', '%s', '%s'",
            CorrectAnswer.FIRST_ANSWER.enumValue,
            CorrectAnswer.SECOND_ANSWER.enumValue,
            CorrectAnswer.THIRD_ANSWER.enumValue);

    public InvalidQuestionCorrectAnswerException() {
        super(MESSAGE);
    }
}
