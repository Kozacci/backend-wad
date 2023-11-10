package pl.uwm.wateradventure.models.participants;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.List;

@Entity
@Table(name = "participants")
@Getter
@Setter
@NoArgsConstructor
public class ParticipantEntity extends WaterAdventureChangeMetricEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "participant")
    private List<ParticipantCourseEntity> participantCourses;

}
