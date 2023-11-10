package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventEntity;

@Component
@RequiredArgsConstructor
class EventDeleter {

    private final EventRepository repository;

    public void deleteEvent(EventEntity event) {
        repository.delete(event);
    }

}
