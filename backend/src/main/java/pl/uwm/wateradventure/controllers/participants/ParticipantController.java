package pl.uwm.wateradventure.controllers.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilterDTO;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantCourseFiltersDTO;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.services.participants.ParticipantFacade;

import java.util.List;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Participant Entity
 * @Endpoint: participants
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participants")
class ParticipantController {

    private final ParticipantFacade participantFacade;

    @GetMapping("/{email}")
    ParticipantEntityDTO getParticipantByEmail(@PathVariable String email) {
        return participantFacade.getParticipantByEmail(email);
    }

    @GetMapping("/{participantId}/courses")
    ResponseEntity<List<CourseFilterDTO>> getCoursesByParticipantIdAndFilters(@PathVariable Long participantId,
                                                                    @RequestParam(required = false) String courseType,
                                                                    @RequestParam(required = false) String courseStatus,
                                                                    @RequestParam(required = false) Boolean isPaid,
                                                                    @RequestParam(required = false) Boolean isPassed,
                                                                    @RequestParam(required = false) String sortBy) {
        var filters = new ParticipantCourseFiltersDTO(participantId, courseType, courseStatus, isPaid, isPassed, sortBy);
        var filteredParticipantCourses = participantFacade.getCoursesByParticipant(filters);
        return !filteredParticipantCourses.isEmpty() ? ResponseEntity.ok(filteredParticipantCourses) : ResponseEntity.noContent().build();
    }


}
