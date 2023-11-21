package pl.uwm.wateradventure.services.learning.catergory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningUpdateDTO;

@Service
@RequiredArgsConstructor
public class CategoryLearningCRUDService {

    private final CategoryLearningUpdater updater;

    public CategoryLearningEntity update(CategoryLearningEntity categoryLearningToChange, CategoryLearningUpdateDTO dto) {
        return updater.update(categoryLearningToChange, dto);
    }
}
