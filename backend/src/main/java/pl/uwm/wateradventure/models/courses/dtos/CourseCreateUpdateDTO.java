package pl.uwm.wateradventure.models.courses.dtos;

import lombok.Builder;

import java.time.LocalDate;

public record CourseCreateUpdateDTO(String courseType,
                                    LocalDate dateFrom,
                                    LocalDate dateTo,
                                    String city,
                                    Integer maxParticipantsNumber) {

    @Builder
    public CourseCreateUpdateDTO {}
}
