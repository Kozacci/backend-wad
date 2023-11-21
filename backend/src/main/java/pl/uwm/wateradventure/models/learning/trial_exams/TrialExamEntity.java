package pl.uwm.wateradventure.models.learning.trial_exams;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;

@Entity
@Table(name = "trial_exams")
@Getter
@Setter
@NoArgsConstructor
public class TrialExamEntity extends WaterAdventureEntity {

    private Integer total;

    private Integer passed;

    private Integer failed;

    @ManyToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistory;

}
