package pl.uwm.wateradventure.models.learning.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;

@Entity
@Table(name = "category_learning")
@Getter
@Setter
@NoArgsConstructor
public class CategoryLearningEntity extends WaterAdventureEntity {

    @Column(name = "questions_answered")
    private Integer questionsAnswered;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistory;

}
