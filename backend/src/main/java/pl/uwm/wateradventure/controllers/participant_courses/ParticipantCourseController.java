package pl.uwm.wateradventure.controllers.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.participant_courses.ParticipantCourseFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participantcourses")
class ParticipantCourseController {

    private final ParticipantCourseFacade participantCourseFacade;

}
