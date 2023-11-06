package pl.uwm.wateradventure.global;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // entity to listen to the changes of creation/update
@Getter
@Setter
public class WaterAdventureChangeMetricEntity extends WaterAdventureEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp // at every update of entity - the date is automatically updated
    @Column(name = "modified_at")
    private Date modifiedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp // at first save (insert) of entity - the date is automatically created
    @Column(name = "created_at")
    private Date createdAt;

    // TODO - methods to initialize the user change metric (user login / admin)
}
