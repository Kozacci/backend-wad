package pl.uwm.wateradventure.services.learning.answers_history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

@Component
@RequiredArgsConstructor
class AnswerHistoryReader {

    private final AnswerHistoryRepository repository;

    // creates new AnswerHistoryEntity if wanted one does not exist
    public AnswerHistoryEntity getAnswerHistoryByParticipantCourse(ParticipantCourseEntity participantCourse) {
        return repository.getAnswerHistoryByParticipantCourseId(participantCourse.getId())
                .orElse(repository.saveAndFlush(new AnswerHistoryEntity(participantCourse)));
    }
}
