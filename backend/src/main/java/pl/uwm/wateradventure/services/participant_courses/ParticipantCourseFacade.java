package pl.uwm.wateradventure.services.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.participant_courses.crud.ParticipantCourseCRUDService;

@Component
@RequiredArgsConstructor
public class ParticipantCourseFacade {

    private final ParticipantCourseCRUDService participantCourseCRUDService;
}
