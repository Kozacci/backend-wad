package pl.uwm.wateradventure.models.learning.answershistory;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.List;

@Entity
@Table(name = "answers_history")
@Getter
@Setter
@NoArgsConstructor
public class AnswerHistoryEntity extends WaterAdventureEntity {

    @OneToMany(mappedBy = "answerHistory")
    private List<GeneralLearningEntity> generalLearningList;

    @OneToMany(mappedBy = "answerHistory")
    private List<CategoryLearningEntity> categoryLearningList;

    @OneToMany(mappedBy = "answerHistory")
    private List<TrialExamEntity> trialExamsList;

    @OneToOne(mappedBy ="answerHistory")
    private ParticipantCourseEntity participantCourseId;

}
