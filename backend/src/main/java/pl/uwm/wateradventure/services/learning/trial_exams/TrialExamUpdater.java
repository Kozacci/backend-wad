package pl.uwm.wateradventure.services.learning.trial_exams;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;

@Component
@RequiredArgsConstructor
class TrialExamUpdater {

    private final TrialExamRepository repository;

    public TrialExamEntity update(TrialExamEntity trialExamToChange, Boolean isPassed) {
        if (isPassed) {
            trialExamToChange.setPassed(trialExamToChange.getPassed() + 1);
        }
        trialExamToChange.setFailed(trialExamToChange.getFailed() + 1);
        trialExamToChange.setTotal(trialExamToChange.getTotal() + 1);
        return repository.saveAndFlush(trialExamToChange);
    }
}
