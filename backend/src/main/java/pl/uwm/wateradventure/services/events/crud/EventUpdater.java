package pl.uwm.wateradventure.services.events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCostException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidDateException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidMaxParticipantsValueException;
import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.EventType;
import pl.uwm.wateradventure.models.events.dtos.EventCreateUpdateDTO;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
class EventUpdater {

    private final EventRepository eventRepository;

    public EventEntityDTO updateEvent(EventEntity eventToUpdate, EventCreateUpdateDTO dto) {
        checkType(eventToUpdate, dto.type());
        checkCost(eventToUpdate, dto.cost());
        checkDate(eventToUpdate, dto.date());
        checkCity(eventToUpdate, dto.city());
        checkDuration(eventToUpdate, dto.duration());
        checkMaxParticipantNumber(eventToUpdate, dto.maxParticipantsNumber());
        eventRepository.saveAndFlush(eventToUpdate);
        return eventToUpdate.toDTO();
    }

    private void checkType(EventEntity event, String type) {
        if (type == null) return;

        var newEventType = EventType.getEventType(type);
        event.setType(newEventType);
    }

    private void checkCost(EventEntity event, Double cost) {
        if (cost == null) return;

        if (cost <= 0.0) {
            throw new InvalidCostException("Cost must be higher than zero.");
        }
        event.setCost(cost);
    }

    private void checkDate(EventEntity event, LocalDateTime date) {
        if (date == null) return;

        if (date.isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("eventDate", "Event date must be future date.");
        }
        event.setDate(date);
    }

    private void checkCity(EventEntity event, String city) {
        if (city == null) return;

        var newEventCity = EventCity.getEventCity(city);
        event.setCity(newEventCity);
    }

    private void checkDuration(EventEntity event, LocalTime duration) {
        if (duration == null) return;

        if (duration.isBefore(LocalTime.of(1,0))) {
            throw new InvalidDateException("eventDuration", "Event must last a minimum of one hour.");
        }
        event.setDuration(duration);
    }

    private void checkMaxParticipantNumber(EventEntity event, Integer maxParticipantNumber) {
        if (maxParticipantNumber == null) return;

        var amountOfAssignedParticipants = event.getEventParticipants().size();

        if (maxParticipantNumber <= 0) {
            throw new InvalidMaxParticipantsValueException("Maximum participants number must be higher than zero.");
        }

        if (amountOfAssignedParticipants >= maxParticipantNumber) {
            throw new InvalidMaxParticipantsValueException(
                    "You can't change number of maximum participants for " + maxParticipantNumber +
                            ", because there are already " + amountOfAssignedParticipants + " " +
                            "participants assigned to this event.");
        }

        event.setMaxParticipantsNumber(maxParticipantNumber);
    }

}
