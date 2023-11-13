package pl.uwm.wateradventure.services.learning.trial_exams;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamUpdateDTO;

@Component
@RequiredArgsConstructor
class TrialExamUpdater {

    private final TrialExamRepository repository;

    public TrialExamEntity update(TrialExamEntity trialExamToChange, TrialExamUpdateDTO dto) {
        trialExamToChange.incrementTotal();
        trialExamToChange.incrementPassedOrFailed(dto.isPassed());
        return repository.saveAndFlush(trialExamToChange);
    }
}
