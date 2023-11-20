package pl.uwm.wateradventure.services.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseEntityDTO;
import pl.uwm.wateradventure.services.courses.crud.CourseCRUDService;
import pl.uwm.wateradventure.services.participant_courses.crud.ParticipantCourseCRUDService;
import pl.uwm.wateradventure.services.participants.crud.ParticipantCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantCourseFacade {

    private final ParticipantCourseCRUDService participantCourseCRUDService;
    private final ParticipantCRUDService participantCRUDService;
    private final CourseCRUDService courseCRUDService;

    public ParticipantCourseEntity update(Long participantCourseId, Boolean isPassed, Boolean isPaid) {
        return participantCourseCRUDService.update(participantCourseId, isPassed, isPaid);
    }

    public ParticipantCourseEntity signIn(Long participantId, Long courseId) {
        var participant = participantCRUDService.getParticipantById(participantId);
        var course = courseCRUDService.getCourseById(courseId);
        return participantCourseCRUDService.signIn(participant, course);
    }

    public List<ParticipantCourseEntityDTO> getParticipantCoursesByParticipantId(Long participantId) {
        return participantCourseCRUDService.getParticipantCoursesByParticipantId(participantId);
    }
}
