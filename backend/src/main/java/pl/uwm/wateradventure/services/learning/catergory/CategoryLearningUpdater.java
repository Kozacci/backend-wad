package pl.uwm.wateradventure.services.learning.catergory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningUpdateDTO;

@Component
@RequiredArgsConstructor
class CategoryLearningUpdater {

    private final CategoryLearningRepository repository;

    public CategoryLearningEntity update(CategoryLearningEntity categoryLearningToChange, CategoryLearningUpdateDTO dto) {
        categoryLearningToChange.addCorrectAnswers(dto.correctAnswers());
        categoryLearningToChange.addQuestionsAnswered(dto.questionsAnswered());
        return repository.saveAndFlush(categoryLearningToChange);
    }

}
