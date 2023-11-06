package pl.uwm.wateradventure.models.events;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.global.WaterAdventureChangeMetricEntity;

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

    private String city;

    @Column(name = "orderer_email")
    private String ordererEmail;

    @Column(name = "orderer_first_name")
    private String ordererFirstName;

    @Column(name = "orderer_last_name")
    private String ordererLastName;

    @Column(name = "orderer_phone_number")
    private String ordererPhoneNumber;

}
