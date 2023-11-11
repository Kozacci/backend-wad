package pl.uwm.wateradventure.models.courses.dtos;

import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseStatus;
import pl.uwm.wateradventure.models.courses.CourseType;

import java.time.LocalDate;

public record CourseFilterDTO(Long id,
                              LocalDate dateFrom,
                              LocalDate dateTo,
                              CourseStatus courseStatus,
                              Integer maxParticipantsNumber,
                              CourseCity city,
                              CourseType courseType,
                              Long registeredParticipants) {
}
