package pl.uwm.wateradventure.services.learning.general;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;

@Service
@RequiredArgsConstructor
public class GeneralLearningCRUDService {

    private final GeneralLearningUpdater updater;

    public GeneralLearningEntity update(
            GeneralLearningEntity generalLearningToChange,
            Boolean isCorrectAnswer
    ) {
        return updater.update(generalLearningToChange, isCorrectAnswer);
    }

}
