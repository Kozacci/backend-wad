package pl.uwm.wateradventure.models.learning.general;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;

@Entity
@Table(name = "general_learning")
@Getter
@Setter
@NoArgsConstructor
public class GeneralLearningEntity extends WaterAdventureEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "course_type")
    private CourseType courseType;

    @Column(name = "questions_answered")
    private Integer questionsAnswered;

    @Column(name = "correct_answers")
    private Integer correctAnswers;

    @ManyToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistoryId;

}
