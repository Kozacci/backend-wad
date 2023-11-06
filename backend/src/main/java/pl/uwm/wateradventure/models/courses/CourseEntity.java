package pl.uwm.wateradventure.models.courses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.global.WaterAdventureChangeMetricEntity;

import java.util.Date;

@Entity
@Table(name = "courses", schema = "wateradventure")
@Getter
@Setter
@NoArgsConstructor
public class CourseEntity extends WaterAdventureChangeMetricEntity {

    @Enumerated(EnumType.STRING)
    private CourseType type;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_from")
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_to")
    private Date dateTo;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;
}
