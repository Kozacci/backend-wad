package pl.uwm.wateradventure.models.global;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/** Global abstract super class with time-change metrics
 * @1 It listens(with AuditingEntityListener) for changes of create/update operations over entities in system
 * @2 It automatically updates or creates the timestamps at every update/create of entity object
 */
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class WaterAdventureChangeMetricEntity extends WaterAdventureEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

}
