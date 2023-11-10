package pl.uwm.wateradventure.services.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.dtos.EventFilteredDTO;
import pl.uwm.wateradventure.models.events.dtos.EventFiltersDTO;
import pl.uwm.wateradventure.services.events.crud.EventCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventFacade {

    private final EventCRUDService eventCRUDService;

    public EventEntity getEventById(Long eventId) {
        return eventCRUDService.getEventById(eventId);
    }

    public void deleteEventById(Long eventId) {
        eventCRUDService.deleteEventById(eventId);
    }

    public List<EventFilteredDTO> getEventsByFilers(EventFiltersDTO filters) {
        return eventCRUDService.getEventsByFilters(filters);
    }

}
