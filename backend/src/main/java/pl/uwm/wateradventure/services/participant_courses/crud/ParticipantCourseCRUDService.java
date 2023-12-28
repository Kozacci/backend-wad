package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

@Service
@RequiredArgsConstructor
public class ParticipantCourseCRUDService {

    private final ParticipantCourseCreator creator;
    private final ParticipantCourseReader reader;
    private final ParticipantCourseUpdater updater;
    private final ParticipantCourseDeleter deleter;

    public ParticipantCourseEntity signIn(ParticipantEntity participant, CourseEntity course) {
        return creator.signIn(participant, course);
    }

    public ParticipantCourseEntity getParticipantCourseById(Long participantCourseId) {
        return reader.getParticipantCourseById(participantCourseId);
    }

    public ParticipantCourseEntity update(Long participantCourseId, Boolean isPassed, Boolean isPaid) {
        var participantCourseEntity = reader.getParticipantCourseById(participantCourseId);
        return updater.update(participantCourseEntity, isPassed, isPaid);
    }

    public void deleteAssigningForCourse(Long participantCourseId) {
        var participantCourseEntity = reader.getParticipantCourseById(participantCourseId);
        deleter.deleteAssigningForCourse(participantCourseEntity);
    }

}
