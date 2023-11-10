package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.events.EventEntity;

@Service
@RequiredArgsConstructor
public class EventCRUDService {

    private final EventReader reader;
    private final EventDeleter deleter;

    public EventEntity getEventById(Long eventId) {
        return reader.getEventById(eventId);
    }

    public void deleteEventById(Long eventId) {
        var eventToDelete = reader.getEventById(eventId);
        deleter.deleteEvent(eventToDelete);
    }
}
