package pl.uwm.wateradventure.services.learning.trial_exams;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;

@Service
@RequiredArgsConstructor
public class TrialExamCRUDService {

    private final TrialExamUpdater updater;

    public TrialExamEntity update(TrialExamEntity trialExamToChange, Boolean isPassed) {
        return updater.update(trialExamToChange, isPassed);
    }
}
