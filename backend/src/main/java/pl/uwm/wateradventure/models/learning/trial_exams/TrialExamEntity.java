package pl.uwm.wateradventure.models.learning.trial_exams;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamDTO;

@Entity
@Table(name = "trial_exams")
@Getter
@Setter
@NoArgsConstructor
public class TrialExamEntity extends WaterAdventureEntity {

    private Integer total;

    private Integer passed;

    private Integer failed;

    @OneToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistory;

    public TrialExamEntity(AnswerHistoryEntity answerHistory) {
        this.total = 0;
        this.passed = 0;
        this.failed = 0;
        this.answerHistory = answerHistory;
    }

    public TrialExamDTO toDTO() {
        return TrialExamDTO.builder()
                .id(this.id)
                .total(this.total)
                .passed(this.passed)
                .failed(this.failed)
                .build();
    }

    public void incrementPassedOrFailed(Boolean isPassed) {
        if (isPassed) {
            this.passed += 1;
        }
        if (!isPassed) {
            this.failed += 1;
        }
    }

    public void incrementTotal() {
        this.total++;
    }

}
