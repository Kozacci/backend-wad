package pl.uwm.wateradventure.models.participants.dtos;

import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseStatus;
import pl.uwm.wateradventure.models.courses.CourseType;

import java.time.LocalDate;

public record ParticipantCourseFilterDTO(Long id,
                                         LocalDate dateFrom,
                                         LocalDate dateTo,
                                         CourseStatus courseStatus,
                                         Integer maxParticipantsNumber,
                                         CourseCity city,
                                         CourseType courseType,
                                         Long registeredParticipants,
                                         Long participantCourseId,
                                         Long participantId,
                                         Boolean isPassed,
                                         Boolean isPaid,
                                         String participantFirstName,
                                         String participantLastName,
                                         String participantEmail,
                                         String participantPhoneNumber) {
}
