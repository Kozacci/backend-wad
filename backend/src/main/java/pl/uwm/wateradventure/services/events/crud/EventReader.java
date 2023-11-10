package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.events.EventEntity;

@Component
@RequiredArgsConstructor
class EventReader {

    private final EventRepository repository;

    public EventEntity getEventById(Long eventId) {
        return repository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("event", "Event with id: " + eventId + " does not exist!"));
    }

}
