package pl.uwm.wateradventure.services.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.dtos.EventCreateUpdateDTO;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;
import pl.uwm.wateradventure.models.events.dtos.EventFilteredDTO;
import pl.uwm.wateradventure.models.events.dtos.EventFiltersDTO;
import pl.uwm.wateradventure.services.events.crud.EventCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventFacade {

    private final EventCRUDService eventCRUDService;

    public EventEntityDTO addEvent(EventCreateUpdateDTO eventCreateDTO) {
        return eventCRUDService.addEvent(eventCreateDTO);
    }

    public EventEntity getEventById(Long eventId) {
        return eventCRUDService.getEventById(eventId);
    }

    public List<EventFilteredDTO> getEventsByFilers(EventFiltersDTO filters) {
        return eventCRUDService.getEventsByFilters(filters);
    }

    public EventEntityDTO updateEvent(Long eventId, EventCreateUpdateDTO eventUpdateDTO) {
        return eventCRUDService.updateEvent(eventId, eventUpdateDTO);
    }

    public void deleteEventById(Long eventId) {
        eventCRUDService.deleteEventById(eventId);
    }



}
