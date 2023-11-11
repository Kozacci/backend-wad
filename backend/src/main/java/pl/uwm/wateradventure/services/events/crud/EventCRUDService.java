package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.dtos.EventCreateUpdateDTO;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;
import pl.uwm.wateradventure.models.events.dtos.EventFilteredDTO;
import pl.uwm.wateradventure.models.events.dtos.EventFiltersDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventCRUDService {

    private final EventCreator creator;
    private final EventReader reader;
    private final EventUpdater updater;
    private final EventDeleter deleter;

    public EventEntityDTO addEvent(EventCreateUpdateDTO eventCreateDTO) {
        return creator.addEvent(eventCreateDTO);
    }

    public EventEntity getEventById(Long eventId) {
        return reader.getEventById(eventId);
    }

    public Page<EventEntityDTO> getAllEventsPageable() {
        return reader.getAllEventsPageable();
    }

    public List<EventFilteredDTO> getEventsByFilters(EventFiltersDTO filters) {
        return reader.getEventsByFilters(filters);
    }

    public EventEntityDTO updateEvent(Long eventId, EventCreateUpdateDTO eventUpdateDTO) {
        return updater.updateEvent(eventId, eventUpdateDTO);
    }

    public void deleteEventById(Long eventId) {
        deleter.deleteEvent(eventId);
    }

}
