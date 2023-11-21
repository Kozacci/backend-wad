package pl.uwm.wateradventure.models.participant_events;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.participant_events.dtos.ParticipantEventEntityDTO;

@Entity
@Table(name = "participant_events")
@Getter
@Setter
@NoArgsConstructor
public class ParticipantEventEntity extends WaterAdventureChangeMetricEntity {

    @Column(name = "orderer_email")
    private String ordererEmail;

    @Column(name = "orderer_first_name")
    private String ordererFirstName;

    @Column(name = "orderer_last_name")
    private String ordererLastName;

    @Column(name = "orderer_phone_number")
    private String ordererPhoneNumber;

    @Column(name = "participants_number")
    private Integer participantsNumber;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private EventEntity event;

    public ParticipantEventEntity(EventEntity event, String ordererEmail, String ordererFirstName,
                                  String ordererLastName, String ordererPhoneNumber,
                                  Integer participantsNumber) {
        this.event = event;
        this.ordererEmail = ordererEmail;
        this.ordererFirstName = ordererFirstName;
        this.ordererLastName = ordererLastName;
        this.ordererPhoneNumber = ordererPhoneNumber;
        this.participantsNumber = participantsNumber;
        this.isPaid = false;
    }

    public ParticipantEventEntityDTO toDTO() {
        return ParticipantEventEntityDTO.builder()
                .participantEventId(this.id)
                .ordererEmail(this.ordererEmail)
                .ordererFirstName(this.ordererEmail)
                .ordererLastName(this.ordererEmail)
                .ordererPhoneNumber(this.ordererEmail)
                .participantsNumber(this.participantsNumber)
                .isPaid(this.isPaid)
                .eventDate(this.event.getDate())
                .eventCity(this.event.getCity().enumValue)
                .eventDuration(this.event.getDuration())
                .eventMaxParticipantsNumber(this.event.getMaxParticipantsNumber())
                .assignedParticipantsNumber(this.event.getAmountOfAssignedParticipants())
                .build();
    }
}
