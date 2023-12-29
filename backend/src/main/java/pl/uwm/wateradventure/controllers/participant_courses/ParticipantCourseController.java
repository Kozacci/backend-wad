package pl.uwm.wateradventure.controllers.participant_courses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseEntityDTO;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ParticipantCourseEntityDTO getParticipantCourseById(@PathVariable Long id) {
        return participantCourseFacade.getParticipantCourseById(id);
    }

    @PostMapping("/{participantId}/sign-in/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    ParticipantCourseEntityDTO signIn(@PathVariable Long participantId,
                                      @PathVariable Long courseId) {
        return participantCourseFacade.signIn(participantId, courseId).toDTO();
    }

//    @AdminOnly
    @PutMapping("/{participantCourseId}")
    @ResponseStatus(HttpStatus.OK)
    ParticipantCourseEntityDTO update(@PathVariable Long participantCourseId,
                                      @RequestParam(required = false) Boolean isPassed,
                                      @RequestParam(required = false) Boolean isPaid) {

        return participantCourseFacade.update(participantCourseId, isPassed, isPaid).toDTO();
    }

    @DeleteMapping("/{participantCourseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAssigningForCourse(@PathVariable Long participantCourseId) {
        participantCourseFacade.deleteAssigningForCourse(participantCourseId);
    }

}
