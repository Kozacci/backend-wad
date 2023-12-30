package pl.uwm.wateradventure.services.learning.general;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;

@Component
@RequiredArgsConstructor
class GeneralLearningUpdater {

    private final GeneralLearningRepository repository;

    public GeneralLearningEntity update(
            GeneralLearningEntity generalLearningToChange,
            Boolean isCorrectAnswer
    ) {
        if(isCorrectAnswer) {
            generalLearningToChange.addCorrectAnswers(1);
            generalLearningToChange.addQuestionsAnswered(1);
        }
        else {
            generalLearningToChange.addQuestionsAnswered(1);
        }
        return repository.saveAndFlush(generalLearningToChange);
    }

}
