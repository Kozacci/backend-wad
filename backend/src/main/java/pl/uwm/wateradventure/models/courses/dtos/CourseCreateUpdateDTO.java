package pl.uwm.wateradventure.models.courses.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import pl.uwm.wateradventure.models.validators.CourseCity;
import pl.uwm.wateradventure.models.validators.CourseType;

import java.time.LocalDate;

public record CourseCreateUpdateDTO(@NotNull(message = "'Course type' field must be filled.")
                                    @CourseType
                                    String courseType,
                                    @NotNull(message = "'Date from' field must be filled.")
                                    @Future(message = "'Date from' can't contain past date.")
                                    LocalDate dateFrom,
                                    @NotNull(message = "'Date to' field must be filled.'")
                                    @Future(message = "'Date to' can't contain past date.'")
                                    LocalDate dateTo,
                                    @NotNull(message = "'City' field must be filled.")
                                    @CourseCity
                                    String city,
                                    @NotNull(message = "'Maximum participants number' field must be filled.")
                                    @PositiveOrZero(message = "'Maximum participants number' field must be higher than zero.")
                                    Integer maxParticipantsNumber) {

    @Builder
    public CourseCreateUpdateDTO {}
}
