package pl.uwm.wateradventure.models.courses.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import pl.uwm.wateradventure.models.validators.CourseCity;
import pl.uwm.wateradventure.models.validators.CourseType;

import java.time.LocalDate;

public record CourseCreateUpdateDTO(@NotNull(message = "Pole 'typ kursu' nie może być puste.")
                                    @CourseType
                                    String courseType,
                                    @NotNull(message = "Pole 'data od' nie może być puste.")
                                    @Future(message = "Pole 'data od' nie może zawierać daty przeszłej.")
                                    LocalDate dateFrom,
                                    @NotNull(message = "Pole 'data do' nie może być puste.")
                                    @Future(message = "Pole 'data do' nie może zawierać daty przeszłej.")
                                    LocalDate dateTo,
                                    @NotNull(message = "Pole 'miasto' nie może być puste.")
                                    @CourseCity
                                    String city,
                                    @NotNull(message = "Pole 'maksymalna liczba kursantów' nie może być puste.")
                                    @PositiveOrZero(message = "Pole 'maksymalna liczba kursantów' musi być większe od zera.")
                                    Integer maxParticipantsNumber) {

    @Builder
    public CourseCreateUpdateDTO {}
}
