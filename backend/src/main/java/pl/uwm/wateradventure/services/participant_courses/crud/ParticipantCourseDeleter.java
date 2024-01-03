package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.CourseCancellationTimeoutException;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EventCancellationTimeoutException;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class ParticipantCourseDeleter {

    private final ParticipantCourseRepository participantCourseRepository;

    public void deleteAssigningForCourse(ParticipantCourseEntity participantCourseEntity) {
        // TODO: tutaj testy
        Boolean isPaid = participantCourseEntity.getIsPaid();
        if(isPaid) {
            throw new CourseCancellationTimeoutException();
        }
        participantCourseRepository.delete(participantCourseEntity);
    }
}
