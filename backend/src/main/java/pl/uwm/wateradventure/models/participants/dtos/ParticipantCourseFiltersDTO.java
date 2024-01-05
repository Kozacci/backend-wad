package pl.uwm.wateradventure.models.participants.dtos;

import java.time.LocalDate;

public record ParticipantCourseFiltersDTO(Long participantId,
                                          String courseType,
                                          String courseStatus,
                                          Boolean isPaid,
                                          Boolean isPassed,
                                          String sortBy,
                                          String email,
                                          String lastName,
                                          LocalDate dateFrom,
                                          String courseCity,
                                          String phoneNumber) {
}
