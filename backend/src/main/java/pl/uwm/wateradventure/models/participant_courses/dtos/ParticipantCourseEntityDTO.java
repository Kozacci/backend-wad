package pl.uwm.wateradventure.models.participant_courses.dtos;

import lombok.Builder;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;

import java.util.Date;

public record ParticipantCourseEntityDTO(Date accessDate,
                                         Boolean isPassed,
                                         Boolean isPaid,
                                         Boolean onlinePayment,
                                         CourseEntityDTO course,
                                         ParticipantEntityDTO participant,
                                         AnswerHistoryEntity answerHistory) {

    @Builder
    public ParticipantCourseEntityDTO{};
}
