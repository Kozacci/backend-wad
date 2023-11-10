package pl.uwm.wateradventure.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.exceptions.custom_exceptions.InvalidQuestionCategoryException;
import pl.uwm.wateradventure.exceptions.custom_exceptions.InvalidQuestionCorrectAnswerException;

import java.util.List;

import static pl.uwm.wateradventure.exceptions.ErrorMessage.extractFieldName;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundException(EntityNotFoundException exception) {
        return new ErrorMessage(exception.entityName, exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        return result.getFieldErrors().stream()
                .map(fieldError -> new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErrorMessage> constraintViolationException(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(violation -> new ErrorMessage(extractFieldName(violation), violation.getMessage()))
                .toList();
    }

    @ExceptionHandler(value = InvalidQuestionCategoryException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidQuestionCategoryException(InvalidQuestionCategoryException exception) {
        return new ErrorMessage("Question category", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidQuestionCorrectAnswerException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidQuestionCorrectAnswerException(InvalidQuestionCorrectAnswerException exception) {
        return new ErrorMessage("Correct answer", exception.getMessage());
    }
}
