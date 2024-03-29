package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.EventType;
import pl.uwm.wateradventure.models.events.dtos.EventCreateUpdateDTO;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;

@Component
@RequiredArgsConstructor
public class EventCreator {

    private final EventRepository eventRepository;

    public EventEntityDTO addEvent(EventCreateUpdateDTO eventCreateDTO) {
        EventEntity newEvent = new EventEntity(
                EventType.getEventType(eventCreateDTO.type()),
                eventCreateDTO.cost(),
                eventCreateDTO.date(),
                EventCity.getEventCity(eventCreateDTO.city()),
                eventCreateDTO.duration(),
                eventCreateDTO.maxParticipantsNumber()
        );
        eventRepository.saveAndFlush(newEvent);
        return newEvent.toDTO();
    }
}
