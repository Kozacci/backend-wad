package pl.uwm.wateradventure.controllers.events;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;
import pl.uwm.wateradventure.services.events.EventFacade;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Event Entity
 * @Endpoint: events
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
class EventController {

    private final EventFacade eventFacade;

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    EventEntityDTO getEventById(@PathVariable Long eventId) {
        return eventFacade.getEventById(eventId).toDTO();
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEventById(@PathVariable Long eventId) {
        eventFacade.deleteEventById(eventId);
    }

}
