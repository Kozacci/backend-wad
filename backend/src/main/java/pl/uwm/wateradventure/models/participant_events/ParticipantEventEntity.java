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

    public ParticipantEventEntity(String ordererEmail, String ordererFirstName,
                                  String ordererLastName, String ordererPhoneNumber,
                                  Integer participantsNumber, Boolean isPaid) {
        this.ordererEmail = ordererEmail;
        this.ordererFirstName = ordererFirstName;
        this.ordererLastName = ordererLastName;
        this.ordererPhoneNumber = ordererPhoneNumber;
        this.participantsNumber = participantsNumber;
        this.isPaid = isPaid;
    }

    public ParticipantEventEntityDTO toDTO() {
        return ParticipantEventEntityDTO.builder()
                .ordererEmail(this.ordererEmail)
                .ordererFirstName(this.ordererEmail)
                .ordererLastName(this.ordererEmail)
                .ordererPhoneNumber(this.ordererEmail)
                .participantsNumber(this.participantsNumber)
                .isPaid(this.isPaid)
                .build();
    }
}
