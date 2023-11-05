package pl.uwm.wateradventure.models.courses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.global.WaterAdventureEntity;

import java.util.Date;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class CourseEntity extends WaterAdventureEntity {

    @Enumerated(EnumType.STRING)
    private CourseType type;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_from")
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_to")
    private Date dateTo;

    private String status;
}
