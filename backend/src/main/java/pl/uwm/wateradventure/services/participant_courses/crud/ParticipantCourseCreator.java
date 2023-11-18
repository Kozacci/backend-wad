package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.MaxParticipantsNumberExceededException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseStatusException;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseStatus;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
class ParticipantCourseCreator {

    private final ParticipantCourseRepository repository;

    public ParticipantCourseEntity signIn(ParticipantEntity participant, CourseEntity course) {
        // some basic validation
        isMaxParticipantsNumberIsExceeded(course.getParticipants(), course.getMaxParticipantsNumber());
        isCourseStatusNotBegun(course.getStatus());
        return repository.saveAndFlush(new ParticipantCourseEntity(course, participant));
    }

    private void isMaxParticipantsNumberIsExceeded(List<ParticipantCourseEntity> participantsList, Integer maxParticipantsNumber) {
        if (participantsList.size() >= maxParticipantsNumber) {
            throw new MaxParticipantsNumberExceededException();
        }
    }

    private void isCourseStatusNotBegun(CourseStatus courseStatus) {
        if (!courseStatus.equals(CourseStatus.NIEROZPOCZETY)) {
            throw new InvalidCourseStatusException("Nie można zapisać się do rozpoczętego/zakończonego kursu.");
        }
    }

}
