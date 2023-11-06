package pl.uwm.wateradventure.models.participants;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;

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

    @ManyToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answersHistoryId;

}
