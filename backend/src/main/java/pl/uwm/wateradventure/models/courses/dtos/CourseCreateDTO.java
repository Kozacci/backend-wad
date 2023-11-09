package pl.uwm.wateradventure.models.courses.dtos;

import lombok.Builder;
import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseType;

import java.time.LocalDate;

public record CourseCreateDTO(CourseType courseType,
                              LocalDate dateFrom,
                              LocalDate dateTo,
                              CourseCity city,
                              Integer maxParticipantsNumber) {

    @Builder
    public CourseCreateDTO {}
}
