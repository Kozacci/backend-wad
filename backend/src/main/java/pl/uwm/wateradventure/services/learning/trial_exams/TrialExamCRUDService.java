package pl.uwm.wateradventure.services.learning.trial_exams;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamUpdateDTO;

@Service
@RequiredArgsConstructor
public class TrialExamCRUDService {

    private final TrialExamUpdater updater;

    public TrialExamEntity update(TrialExamEntity trialExamToChange, TrialExamUpdateDTO dto) {
        return updater.update(trialExamToChange, dto);
    }
}
