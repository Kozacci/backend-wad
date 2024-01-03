package pl.uwm.wateradventure.services.participant_events.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.NotEnoughSeatsForEventException;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityCreateDTO;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityDTO;

@Component
@RequiredArgsConstructor
class ParticipantEventCreator {

    private final ParticipantEventRepository repository;

    public ParticipantEventEntityDTO signInForEvent(EventEntity event, ParticipantEventEntityCreateDTO dto) {
        // TODO: testy tutaj
        checkIfThereAreEnoughSeats(event, dto);
        var participantEvent = new ParticipantEventEntity(event, dto.ordererEmail(),
                dto.ordererFirstName(), dto.ordererLastName(),
                dto.ordererPhoneNumber(), dto.participantsNumber());
        var savedParticipantEvent = repository.saveAndFlush(participantEvent);
        // some bug here appears, assignedParticipantsNumber in ParticipantEventEntityDTO has only assignedParticipantsNumber from the past,
        // without those given in ParticipantEventEntityCreateDTO
        return savedParticipantEvent.toDTO();
    }

    private void checkIfThereAreEnoughSeats(EventEntity event, ParticipantEventEntityCreateDTO dto) {
        var assignedParticipants = event.getAmountOfAssignedParticipants();
        var eventMaxParticipantsNumber = event.getMaxParticipantsNumber();
        if (assignedParticipants + dto.participantsNumber() > eventMaxParticipantsNumber) {
            var seatsLeft = eventMaxParticipantsNumber - assignedParticipants;
            throw new NotEnoughSeatsForEventException("You can't buy " + dto.participantsNumber() +
                    " seats, there are only: " + seatsLeft + " seats left.");
        }
    }

}
