package pl.uwm.wateradventure.models.participant_courses.dtos;

import java.util.List;

public record ParticipantCourseUpdateDTO(List<Long> participantCourseIds, Boolean isPassed, Boolean hasAccess) {
}
