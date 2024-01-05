package pl.uwm.wateradventure.services.participant_events.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventInfoDTO;

import java.util.List;

@Repository
interface ParticipantEventRepository extends JpaRepository<ParticipantEventEntity, Long> {

    @Query("""
            select new pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventInfoDTO(
            participantEvent.id,
            participantEvent.ordererEmail,
            participantEvent.ordererFirstName,
            participantEvent.ordererLastName,
            participantEvent.ordererPhoneNumber,
            participantEvent.participantsNumber,
            participantEvent.isPaid
            )
            FROM ParticipantEventEntity participantEvent
            WHERE participantEvent.event.id = :eventId
            """)
    List<ParticipantEventInfoDTO> getAssignedParticipantsForAnEventByEventId(@Param("eventId") Long eventId);
}
