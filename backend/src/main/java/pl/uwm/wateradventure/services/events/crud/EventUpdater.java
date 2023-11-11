package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventType;
import pl.uwm.wateradventure.models.events.dtos.EventCreateUpdateDTO;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;

@Component
@RequiredArgsConstructor
class EventUpdater {

    private final EventRepository eventRepository;
    private final EventReader eventReader;

    public EventEntityDTO updateEvent(Long eventId, EventCreateUpdateDTO eventUpdated) {
        var courseToUpdate = eventReader.getEventById(eventId);
        courseToUpdate.setType(EventType.getEventType(eventUpdated.type()));
        courseToUpdate.setCost(eventUpdated.cost());
        courseToUpdate.setDate(eventUpdated.date());
        courseToUpdate.setCity(EventCity.getEventCity(eventUpdated.city()));
        courseToUpdate.setDuration(eventUpdated.duration());
        courseToUpdate.setMaxParticipantsNumber(eventUpdated.maxParticipantsNumber());
        eventRepository.saveAndFlush(courseToUpdate);
        return courseToUpdate.toDTO();
    }

}
