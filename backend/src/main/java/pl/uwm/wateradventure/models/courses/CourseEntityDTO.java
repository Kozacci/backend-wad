package pl.uwm.wateradventure.models.courses;

import lombok.Builder;

import java.time.LocalDate;

public record CourseEntityDTO(String courseType, LocalDate dateFrom, LocalDate dateTo, String courseStatus) {

    @Builder
    public CourseEntityDTO {}
}
