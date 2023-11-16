package pl.uwm.wateradventure.services.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.services.participant_courses.crud.ParticipantCourseCRUDService;

@Component
@RequiredArgsConstructor
public class ParticipantCourseFacade {

    private final ParticipantCourseCRUDService participantCourseCRUDService;

    public ParticipantCourseEntity update(Long participantCourseId, Boolean isPassed, Boolean isPaid) {
        return participantCourseCRUDService.update(participantCourseId, isPassed, isPaid);
    }
}
