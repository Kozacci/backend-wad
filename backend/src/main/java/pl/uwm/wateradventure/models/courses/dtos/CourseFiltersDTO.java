package pl.uwm.wateradventure.models.courses.dtos;

import java.time.LocalDate;

public record CourseFiltersDTO(String courseType,
                               String courseStatus,
                               String courseCity,
                               LocalDate dateFrom,
                               LocalDate dateTo,
                               Integer registeredParticipants,
                               Integer participantsLimit,
                               String sortBy) {
}
