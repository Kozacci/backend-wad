package pl.uwm.wateradventure.services.learning.general;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningUpdateDTO;

@Service
@RequiredArgsConstructor
public class GeneralLearningCRUDService {

    private final GeneralLearningUpdater updater;

    public GeneralLearningEntity update(GeneralLearningEntity generalLearningToChange, GeneralLearningUpdateDTO dto) {
        return updater.update(generalLearningToChange, dto);
    }

}
