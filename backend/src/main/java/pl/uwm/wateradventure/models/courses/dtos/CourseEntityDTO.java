package pl.uwm.wateradventure.models.courses.dtos;

import lombok.Builder;

import java.time.LocalDate;

public record CourseEntityDTO(Long id,
                              String courseType,
                              LocalDate dateFrom,
                              LocalDate dateTo,
                              String courseStatus,
                              Integer maxParticipantsNumber,
                              Integer assignedParticipantsNumber,
                              String city) {

    @Builder
    public CourseEntityDTO {}
}
