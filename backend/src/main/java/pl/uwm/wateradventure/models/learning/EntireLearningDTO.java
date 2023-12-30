package pl.uwm.wateradventure.models.learning;

import lombok.Builder;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntityDTO;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningEntityDTO;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamEntityDTO;

import java.util.List;

public record EntireLearningDTO(Long answerHistoryId,
                                List<CategoryLearningEntityDTO> categoryLearningEntityDTO,
                                TrialExamEntityDTO trialExamEntityDTO,
                                GeneralLearningEntityDTO generalLearningEntityDTO) {

    @Builder public EntireLearningDTO {}
}
