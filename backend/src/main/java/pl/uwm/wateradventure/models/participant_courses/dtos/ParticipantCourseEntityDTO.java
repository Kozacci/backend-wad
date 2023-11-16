package pl.uwm.wateradventure.models.participant_courses.dtos;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ParticipantCourseEntityDTO(Long courseId,
                                         String courseType,
                                         LocalDate courseDateFrom,
                                         LocalDate courseDateTo,
                                         LocalDateTime accessDate,
                                         String participantEmail,
                                         String participantLastName,
                                         Boolean isPassed,
                                         Boolean isPaid,
                                         Boolean onlinePayment
                                         ) {

    @Builder
    public ParticipantCourseEntityDTO{}
}
