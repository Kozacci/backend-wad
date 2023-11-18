package pl.uwm.wateradventure.models.learning.answershistory;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "answers_history")
@Getter
@Setter
@NoArgsConstructor
public class AnswerHistoryEntity extends WaterAdventureEntity {

    @OneToOne(mappedBy = "answerHistory",
              cascade = CascadeType.ALL)
    private GeneralLearningEntity generalLearning;

    @OneToMany(mappedBy = "answerHistory",
              cascade = CascadeType.ALL)
    private List<CategoryLearningEntity> categoryLearningList;

    @OneToOne(mappedBy = "answerHistory",
              cascade = CascadeType.ALL)
    private TrialExamEntity trialExam;

    @OneToOne(mappedBy = "answerHistory")
    private ParticipantCourseEntity participantCourse;

    public AnswerHistoryEntity(ParticipantCourseEntity participantCourse) {
        this.participantCourse = participantCourse;
        this.generalLearning = new GeneralLearningEntity(this);
        this.categoryLearningList = new ArrayList<CategoryLearningEntity>();
        this.trialExam = new TrialExamEntity(this);
    }

}
