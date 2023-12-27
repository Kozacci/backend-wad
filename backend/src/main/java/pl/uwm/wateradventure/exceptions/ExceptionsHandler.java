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
import pl.uwm.wateradventure.exceptions.custom_exceptions.NotEnoughSeatsForEventException;
import pl.uwm.wateradventure.exceptions.custom_exceptions.ParticipantAlreadySignedInException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.*;

import java.util.List;

import static pl.uwm.wateradventure.exceptions.ErrorDTO.extractFieldName;

/** REST Controller created in the needs of exception handling.
 * It consumes custom exceptions and throws them if neccessary
 */
@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDTO entityNotFoundException(EntityNotFoundException exception) {
        return new ErrorDTO(exception.entityName, exception.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErrorDTO> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        return result.getFieldErrors().stream()
                .map(fieldError -> new ErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErrorDTO> constraintViolationException(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream()
                .map(violation -> new ErrorDTO(extractFieldName(violation), violation.getMessage()))
                .toList();
    }

    @ExceptionHandler(value = InvalidCourseTypeException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidCourseTypeException(InvalidCourseTypeException exception) {
        return new ErrorDTO("courseType", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidCourseStatusException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidCourseStatusException(InvalidCourseStatusException exception) {
        return new ErrorDTO("status", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidCourseCityException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidCourseCityException(InvalidCourseCityException exception) {
        return new ErrorDTO("city", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidEventTypeException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidEventTypeException(InvalidEventTypeException exception) {
        return new ErrorDTO("type", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidEventCityException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidEventCityException(InvalidEventCityException exception) {
        return new ErrorDTO("city", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidQuestionCategoryException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidQuestionCategoryException(InvalidQuestionCategoryException exception) {
        return new ErrorDTO("category", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidQuestionCorrectAnswerException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidQuestionCorrectAnswerException(InvalidQuestionCorrectAnswerException exception) {
        return new ErrorDTO("correctAnswer", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidRoleException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidRoleException(InvalidRoleException exception) {
        return new ErrorDTO("role", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidSortByValueException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidSortByValueException(InvalidSortByValueException exception) {
        return new ErrorDTO("sortBy", exception.getMessage());
    }

    @ExceptionHandler(value = MaxParticipantsNumberExceededException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO maxParticipantsNumberExceededException(MaxParticipantsNumberExceededException exception) {
        return new ErrorDTO("course", exception.getMessage());
    }

    @ExceptionHandler(value = ParticipantAlreadySignedInException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO participantAlreadySignedInException(ParticipantAlreadySignedInException exception) {
        return new ErrorDTO("course", exception.getMessage());
    }

    @ExceptionHandler(value = InvalidDateException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidDateException(InvalidDateException exception) {
        return new ErrorDTO(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = InvalidMaxParticipantsValueException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO invalidMaxParticipantsValueException(InvalidMaxParticipantsValueException exception) {
        return new ErrorDTO("maxParticipantsNumber", exception.getMessage());
    }

    @ExceptionHandler(value = VarLengthExceededException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO varLengthExceededException(VarLengthExceededException exception) {
        return new ErrorDTO(exception.fieldName, exception.getMessage());
    }

    @ExceptionHandler(value = NotEnoughSeatsForEventException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO notEnoughSeatsForEventException(NotEnoughSeatsForEventException exception) {
        return new ErrorDTO("participantsNumber", exception.getMessage());
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO emailAlreadyExistsException(EmailAlreadyExistsException exception) {
        return new ErrorDTO("email", exception.getMessage());
    }

}
