package pl.uwm.wateradventure.models.learning;

import lombok.Builder;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningDTO;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningDTO;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamDTO;

import java.util.List;

public record EntireLearningDTO(List<CategoryLearningDTO> categoryLearningDTO,
                                TrialExamDTO trialExamDTO,
                                GeneralLearningDTO generalLearningDTO) {

    @Builder public EntireLearningDTO {}
}
