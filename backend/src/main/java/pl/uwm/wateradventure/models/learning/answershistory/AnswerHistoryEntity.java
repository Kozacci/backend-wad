package pl.uwm.wateradventure.models.learning.answershistory;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;

import java.util.List;

@Entity
@Table(name = "answers_history")
@Getter
@Setter
@NoArgsConstructor
public class AnswerHistoryEntity extends WaterAdventureEntity {

    @OneToMany(mappedBy = "answerHistoryId")
    private List<GeneralLearningEntity> generalLearningList;

    @OneToMany(mappedBy = "answerHistoryId")
    private List<CategoryLearningEntity> categoryLearningList;

    @OneToMany(mappedBy = "answerHistoryId")
    private List<TrialExamEntity> trialExamsList;

}
