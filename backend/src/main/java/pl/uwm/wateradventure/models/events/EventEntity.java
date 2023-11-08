package pl.uwm.wateradventure.models.events;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;

import java.util.Date;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class EventEntity extends WaterAdventureChangeMetricEntity {

    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "is_paid")
    private Boolean isPaid;

    private Double cost;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String address;

    @Column(name = "orderer_email")
    private String ordererEmail;

    @Column(name = "orderer_first_name")
    private String ordererFirstName;

    @Column(name = "orderer_last_name")
    private String ordererLastName;

    @Column(name = "orderer_phone_number")
    private String ordererPhoneNumber;


    public EventEntity(EventType type, Boolean isPaid,
                       Double cost, Date date,
                       String address,
                       String ordererEmail,
                       String ordererFirstName,
                       String ordererLastName,
                       String ordererPhoneNumber) {
        this.type = type;
        this.isPaid = isPaid;
        this.cost = cost;
        this.date = date;
        this.address = address;
        this.ordererEmail = ordererEmail;
        this.ordererFirstName = ordererFirstName;
        this.ordererLastName = ordererLastName;
        this.ordererPhoneNumber = ordererPhoneNumber;
    }

    public EventEntityDTO toDTO() {
        return EventEntityDTO.builder()
                .type(this.type.enumValue)
                .isPaid(this.isPaid)
                .cost(this.cost)
                .date(this.date)
                .address(this.address)
                .ordererEmail(this.ordererEmail)
                .ordererFirstName(this.ordererFirstName)
                .ordererLastName(this.ordererLastName)
                .ordererPhoneNumber(this.ordererPhoneNumber)
                .build();
    }


}
