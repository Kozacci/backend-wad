package pl.uwm.wateradventure.services.learning.general;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningUpdateDTO;

@Component
@RequiredArgsConstructor
public class GeneralLearningUpdater {

    private final GeneralLearningRepository repository;

    public GeneralLearningEntity update(GeneralLearningEntity generalLearningToChange, GeneralLearningUpdateDTO dto) {
        generalLearningToChange.addQuestionsAnswered(dto.questionsAnswered());
        generalLearningToChange.addCorrectAnswers(dto.correctAnswers());
        repository.saveAndFlush(generalLearningToChange);
        return generalLearningToChange;
    }

}
