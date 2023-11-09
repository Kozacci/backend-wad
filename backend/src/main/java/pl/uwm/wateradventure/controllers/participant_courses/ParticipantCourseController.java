package pl.uwm.wateradventure.controllers.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.participant_courses.ParticipantCourseFacade;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Participant Courses Entity
 * @Endpoint: participant-courses
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participant-courses")
class ParticipantCourseController {

    private final ParticipantCourseFacade participantCourseFacade;

}
