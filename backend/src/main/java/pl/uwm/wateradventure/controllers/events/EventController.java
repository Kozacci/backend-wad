package pl.uwm.wateradventure.controllers.events;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventType;
import pl.uwm.wateradventure.models.events.dtos.*;
import pl.uwm.wateradventure.services.events.EventFacade;

import java.util.List;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Event Entity
 * @Endpoint: events
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
class EventController {

    private final EventFacade eventFacade;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    EventEntityDTO addEvent(@RequestBody @Valid EventCreateUpdateDTO eventCreateDTO) {
        return eventFacade.addEvent(eventCreateDTO);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    EventEntityDTO getEventById(@PathVariable Long eventId) {
        return eventFacade.getEventById(eventId).toDTO();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    Page<EventEntityDTO> getAllEventsPageable() {
        return eventFacade.getAllEventsPageable();
    }

    @GetMapping("/events-filter-by")
    ResponseEntity<List<EventFilterDTO>> getEventsByFilters(@RequestParam(required = false) EventType type,
                                                                       @RequestParam(required = false) EventCity city,
                                                                       @RequestParam(required = false) Double cost,
                                                                       @RequestParam(required = false) Integer maxParticipantsNumber,
                                                                       @RequestParam(required = false) String sortBy,
                                                                       @RequestParam(required = false) Boolean adminSearch
    ) {
        var filters = new EventFiltersDTO(type, city, cost, maxParticipantsNumber, sortBy, adminSearch);
        List<EventFilterDTO> filteredEvents = eventFacade.getEventsByFilers(filters);
        return !filteredEvents.isEmpty() ? ResponseEntity.ok(filteredEvents) : ResponseEntity.noContent().build();
    }

    @GetMapping("/participant-events-filter-by")
    ResponseEntity<List<ParticipantEventFilterDTO>> getParticipantEventsByFilters(@RequestParam(required = false) EventType type,
                                                                                  @RequestParam(required = false) EventCity city,
                                                                                  @RequestParam(required = false) String clientLastName,
                                                                                  @RequestParam(required = false) String clientEmail,
                                                                                  @RequestParam(required = false) String sortBy
                                              ) {
        var filters = new ParticipantEventFiltersDTO(type, city, clientLastName, clientEmail, sortBy);
        List<ParticipantEventFilterDTO> filteredEvents = eventFacade.getParticipantEventsByFilers(filters);
        return !filteredEvents.isEmpty() ? ResponseEntity.ok(filteredEvents) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    EventEntityDTO updateEvent(@PathVariable Long eventId, @RequestBody EventCreateUpdateDTO eventUpdateDTO) {
        return eventFacade.updateEvent(eventId, eventUpdateDTO);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEventById(@PathVariable Long eventId) {
        eventFacade.deleteEventById(eventId);
    }

}
