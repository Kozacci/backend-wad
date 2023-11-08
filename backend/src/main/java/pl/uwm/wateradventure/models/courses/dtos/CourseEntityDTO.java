package pl.uwm.wateradventure.models.courses.dtos;

import lombok.Builder;

import java.time.LocalDate;

public record CourseEntityDTO(String courseType,
                              LocalDate dateFrom,
                              LocalDate dateTo,
                              String courseStatus,
                              Integer maxParticipantsNumber,
                              String city) {

    @Builder
    public CourseEntityDTO {}
}
