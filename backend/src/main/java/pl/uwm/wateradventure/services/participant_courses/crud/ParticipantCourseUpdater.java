package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
class ParticipantCourseUpdater {

    private final ParticipantCourseRepository repository;

    public List<ParticipantCourseEntity> update(List<ParticipantCourseEntity> entities, Boolean isPassed, Boolean hasAccess) {
        // TODO: tutaj testy
        for (ParticipantCourseEntity participantCourse: entities) {
            if (isPassed != null) {
                participantCourse.setIsPassed(isPassed);
            }
            if (hasAccess != null) {
                participantCourse.setIsPaid(hasAccess);
                participantCourse.setHasAccess(hasAccess);
                participantCourse.setAccessDate(LocalDateTime.now());
            }
        }
        return repository.saveAllAndFlush(entities);
    }

}
