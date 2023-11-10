package pl.uwm.wateradventure.models.events;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class EventEntity extends WaterAdventureChangeMetricEntity {

    @Enumerated(EnumType.STRING)
    private EventType type;

    private Double cost;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "address")
    private EventCity city;

    @Temporal(TemporalType.TIME)
    private LocalTime duration;

    @Column(name = "max_participants_number")
    private Integer maxParticipantsNumber;

    @OneToMany(mappedBy = "event")
    private List<ParticipantEventEntity> participantEvents;

    public EventEntity(EventType type, Double cost,
                       Date date, EventCity city,
                       LocalTime duration,
                       Integer maxParticipantsNumber) {
        this.type = type;
        this.cost = cost;
        this.date = date;
        this.city = city;
        this.duration = duration;
        this.maxParticipantsNumber = maxParticipantsNumber;
    }


    public EventEntityDTO toDTO() {
        return EventEntityDTO.builder()
                .type(this.type.enumValue)
                .cost(this.cost)
                .date(this.date)
                .city(this.city.enumValue)
                .duration(this.duration)
                .maxParticipantsNumber(this.maxParticipantsNumber)
                .build();
    }


}
