package pl.uwm.wateradventure.models.learning.general;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningDTO;

@Entity
@Table(name = "general_learning")
@Getter
@Setter
@NoArgsConstructor
public class GeneralLearningEntity extends WaterAdventureEntity {

    @Column(name = "questions_answered")
    private Integer questionsAnswered;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @OneToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistory;

    public GeneralLearningEntity(AnswerHistoryEntity answerHistory) {
        this.questionsAnswered = 0;
        this.correctAnswers = 0;
        this.answerHistory = answerHistory;
    }

    public GeneralLearningDTO toDTO() {
        return GeneralLearningDTO.builder()
                .id(this.id)
                .questionsAnswered(this.questionsAnswered)
                .correctAnswers(this.correctAnswers)
                .build();
    }

    public void addQuestionsAnswered(Integer number) {
        this.questionsAnswered += number;
    }

    public void addCorrectAnswers(Integer number) {
        this.correctAnswers += number;
    }

}
