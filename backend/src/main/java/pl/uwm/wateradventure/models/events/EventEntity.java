package pl.uwm.wateradventure.models.events;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "address")
    private EventCity city;

    @Temporal(TemporalType.TIME)
    private LocalTime duration;

    @Column(name = "max_participants_number")
    private Integer maxParticipantsNumber;

    @OneToMany(mappedBy = "event", orphanRemoval = true)
    private List<ParticipantEventEntity> eventParticipants;

    public EventEntity(EventType type, Double cost,
                       LocalDateTime date, EventCity city,
                       LocalTime duration,
                       Integer maxParticipantsNumber) {
        this.type = type;
        this.cost = cost;
        this.date = date;
        this.city = city;
        this.duration = duration;
        this.maxParticipantsNumber = maxParticipantsNumber;
        this.eventParticipants = new ArrayList<>();
    }


    public EventEntityDTO toDTO() {
        return EventEntityDTO.builder()
                .id(this.id)
                .type(this.type.enumValue)
                .cost(this.cost)
                .date(this.date)
                .city(this.city.enumValue)
                .duration(this.duration)
                .maxParticipantsNumber(this.maxParticipantsNumber)
                .build();
    }

    public Integer getAmountOfAssignedParticipants() {
        return this.getEventParticipants().stream()
                .mapToInt(ParticipantEventEntity::getParticipantsNumber)
                .sum();
    }


}
