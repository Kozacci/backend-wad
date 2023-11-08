package pl.uwm.wateradventure.services.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.participant_courses.crud.ParticipantCoursesCRUDService;

@Component
@RequiredArgsConstructor
public class ParticipantCoursesFacade {

    private final ParticipantCoursesCRUDService participantCoursesCRUDService;
}
