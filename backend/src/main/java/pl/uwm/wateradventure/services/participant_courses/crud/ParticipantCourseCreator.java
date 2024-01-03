package pl.uwm.wateradventure.services.participant_courses.crud;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.MaxParticipantsNumberExceededException;
import pl.uwm.wateradventure.exceptions.custom_exceptions.ParticipantAlreadySignedInException;
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

    @Transactional
    public ParticipantCourseEntity signIn(ParticipantEntity participant, CourseEntity course) {
        // some basic validation
        // TODO: tutaj testy
        isMaxParticipantsNumberIsExceeded(course.getParticipants(), course.getMaxParticipantsNumber());
        isCourseStatusNotBegun(course.getStatus());
        isParticipantAlreadySignedInForCourse(participant, course.getParticipants());
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

    private void isParticipantAlreadySignedInForCourse(ParticipantEntity participant, List<ParticipantCourseEntity> participantsList) {
        var isParticipantAlreadySignedIn = participantsList.stream()
                .anyMatch(participantCourse -> participantCourse.getParticipant().getId().equals(participant.getId()));

        if (isParticipantAlreadySignedIn) {
            throw new ParticipantAlreadySignedInException("Już jesteś zapisany na ten kurs.");
        }
    }

}
