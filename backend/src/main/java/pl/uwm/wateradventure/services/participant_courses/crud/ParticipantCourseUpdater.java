package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
class ParticipantCourseUpdater {

    private final ParticipantCourseRepository repository;

    public ParticipantCourseEntity update(ParticipantCourseEntity entity, Boolean isPassed, Boolean isPaid) {
        if (isPassed != null) {
            entity.setIsPassed(isPassed);
        }
        if (isPaid != null) {
            entity.setIsPaid(isPaid);
            if (isPaid) {
                entity.setAccessDate(LocalDateTime.now());
            }
        }
        return repository.saveAndFlush(entity);
    }

}
