package pl.uwm.wateradventure.services.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseUpdateDTO;
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

    public ParticipantCourseEntity signIn(Long participantId, Long courseId) {
        var participant = participantCRUDService.getParticipantById(participantId);
        var course = courseCRUDService.getCourseById(courseId);
        return participantCourseCRUDService.signIn(participant, course);
    }

    public List<ParticipantCourseEntity> update(ParticipantCourseUpdateDTO dto) {
        return participantCourseCRUDService.update(dto);
    }

    public void deleteAssigningForCourse(Long participantCourseId) {
        participantCourseCRUDService.deleteAssigningForCourse(participantCourseId);
    }


}
