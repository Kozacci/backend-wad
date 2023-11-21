package pl.uwm.wateradventure.services.learning.trial_exams;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;

@Component
@RequiredArgsConstructor
class TrialExamUpdater {

    private final TrialExamRepository repository;

    public TrialExamEntity update(TrialExamEntity trialExamToChange, Boolean isPassed) {
        trialExamToChange.incrementTotal();
        trialExamToChange.incrementPassedOrFailed(isPassed);
        return repository.saveAndFlush(trialExamToChange);
    }
}
