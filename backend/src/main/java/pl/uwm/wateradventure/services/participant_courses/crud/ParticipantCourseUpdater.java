package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
class ParticipantCourseUpdater {

    private final ParticipantCourseRepository repository;

    public List<ParticipantCourseEntity> update(List<ParticipantCourseEntity> entities, Boolean isPassed) {
        for (ParticipantCourseEntity participantCourse: entities) {
            participantCourse.setIsPassed(isPassed);
        }
        return repository.saveAllAndFlush(entities);
    }

}
