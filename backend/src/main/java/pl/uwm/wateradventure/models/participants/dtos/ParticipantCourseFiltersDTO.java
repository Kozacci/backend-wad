package pl.uwm.wateradventure.models.participants.dtos;

public record ParticipantCourseFiltersDTO(Long participantId,
                                          String courseType,
                                          String courseStatus,
                                          Boolean isPaid,
                                          Boolean isPassed,
                                          String sortBy) {
}
