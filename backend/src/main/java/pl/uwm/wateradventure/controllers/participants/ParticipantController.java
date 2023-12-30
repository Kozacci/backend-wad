package pl.uwm.wateradventure.controllers.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantCourseFilterDTO;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantCourseFiltersDTO;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantUpdateDTO;
import pl.uwm.wateradventure.services.participants.ParticipantFacade;

import java.time.LocalDate;
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

    // TODO - move below method to participant courses / or move participant events filter method there
    @GetMapping("/courses/filter-by")
    ResponseEntity<List<ParticipantCourseFilterDTO>> getCoursesByParticipantIdAndFilters(
            @RequestParam(required = false) Long participantId,
            @RequestParam(required = false) String courseType,
            @RequestParam(required = false) String courseStatus,
            @RequestParam(required = false) Boolean isPaid,
            @RequestParam(required = false) Boolean isPassed,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String email, // participant Email
            @RequestParam(required = false) String lastName, // participant lastName
            @RequestParam(required = false) LocalDate dateFrom, // course dateFrom
            @RequestParam(required = false) String courseCity, // course dateFrom
            @RequestParam(required = false) String phoneNumber // course dateFrom
    ) {
        var filters = new ParticipantCourseFiltersDTO(participantId, courseType,
                courseStatus, isPaid, isPassed, sortBy, email, lastName, dateFrom, courseCity, phoneNumber);
        var filteredParticipantCourses = participantFacade.getCoursesByParticipant(filters);
        return !filteredParticipantCourses.isEmpty() ? ResponseEntity.ok(filteredParticipantCourses) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{participantId}")
    @ResponseStatus(HttpStatus.OK)
    public ParticipantEntityDTO updateParticipant(@PathVariable Long participantId, @RequestBody ParticipantUpdateDTO participantUpdateDTO) {
        return participantFacade.updateParticipant(participantId, participantUpdateDTO);
    }


}
