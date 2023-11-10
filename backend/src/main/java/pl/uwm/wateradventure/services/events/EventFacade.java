package pl.uwm.wateradventure.services.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.services.events.crud.EventCRUDService;

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
}
