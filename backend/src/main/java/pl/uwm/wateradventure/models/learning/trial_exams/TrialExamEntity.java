package pl.uwm.wateradventure.models.learning.trial_exams;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;

@Entity
@Table(name = "trial_exams")
@Getter
@Setter
@NoArgsConstructor
public class TrialExamEntity extends WaterAdventureEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "course_type")
    private CourseType courseType;

    private Integer total;

    private Integer passed;

    private Integer failed;

    @ManyToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistoryId;

}
