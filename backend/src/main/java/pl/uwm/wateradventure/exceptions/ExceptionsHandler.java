package pl.uwm.wateradventure.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.exceptions.custom_exceptions.MaxParticipantsNumberExceededException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.*;

import java.util.List;

import static pl.uwm.wateradventure.exceptions.ErrorMessage.extractFieldName;

/** REST Controller created in the needs of exception handling.
 * It consumes custom exceptions and throws them if neccessary
 */
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

    @ExceptionHandler(value = InvalidCourseTypeException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidCourseTypeException(InvalidCourseTypeException exception) {
        return new ErrorMessage("courseType", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidCourseStatusException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidCourseStatusException(InvalidCourseStatusException exception) {
        return new ErrorMessage("status", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidCourseCityException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidCourseCityException(InvalidCourseCityException exception) {
        return new ErrorMessage("city", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidEventTypeException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidEventTypeException(InvalidEventTypeException exception) {
        return new ErrorMessage("type", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidEventCityException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidEventCityException(InvalidEventCityException exception) {
        return new ErrorMessage("city", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidQuestionCategoryException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidQuestionCategoryException(InvalidQuestionCategoryException exception) {
        return new ErrorMessage("category", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidQuestionCorrectAnswerException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidQuestionCorrectAnswerException(InvalidQuestionCorrectAnswerException exception) {
        return new ErrorMessage("correctAnswer", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidRoleException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidRoleException(InvalidRoleException exception) {
        return new ErrorMessage("role", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidSortByValueException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage invalidSortByValueException(InvalidSortByValueException exception) {
        return new ErrorMessage("sortBy", exception.getMessage());
    }

    @ExceptionHandler(value = MaxParticipantsNumberExceededException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage maxParticipantsNumberExceededException(MaxParticipantsNumberExceededException exception) {
        return new ErrorMessage("course", exception.getMessage());
    }

}
