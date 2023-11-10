package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventEntity;

@Component
@RequiredArgsConstructor
class EventDeleter {

    private final EventRepository repository;
    private final EventReader reader;

    public void deleteEvent(Long eventId) {
        var eventToDelete = reader.getEventById(eventId);
        repository.delete(eventToDelete);
    }

}
