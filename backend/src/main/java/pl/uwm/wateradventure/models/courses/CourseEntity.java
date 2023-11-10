package pl.uwm.wateradventure.models.courses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;

import java.time.LocalDate;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class CourseEntity extends WaterAdventureChangeMetricEntity {

    @Enumerated(EnumType.STRING)
    private CourseType type;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_to")
    private LocalDate dateTo;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Enumerated(EnumType.STRING)
    private CourseCity city;

    @Column(name = "max_participants_number")
    private Integer maxParticipantsNumber;

    public CourseEntity(CourseType courseType, LocalDate dateFrom,
                        LocalDate dateTo, CourseCity city,
                        Integer maxParticipantsNumber) {
        this.type = courseType;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.city = city;
        this.maxParticipantsNumber = maxParticipantsNumber;
        this.status = CourseStatus.NIEROZPOCZETY;
    }

    public CourseEntityDTO toDTO() {
        return CourseEntityDTO.builder()
                .id(this.id)
                .courseType(this.type.enumValue)
                .dateFrom(this.dateFrom)
                .dateTo(this.dateTo)
                .courseStatus(this.status.enumValue)
                .maxParticipantsNumber(this.maxParticipantsNumber)
                .city(this.city.enumValue)
                .build();
    }

}
