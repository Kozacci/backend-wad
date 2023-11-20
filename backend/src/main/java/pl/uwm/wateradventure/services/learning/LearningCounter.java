package pl.uwm.wateradventure.services.learning;

import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.EntireLearningDTO;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningDTO;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
class LearningCounter {

    public EntireLearningDTO countAllAnswers(AnswerHistoryEntity answerHistory) {
        var categoryLearningEntities = answerHistory.getCategoryLearningList();
        var entitiesGroupedByCategory = groupByCategories(categoryLearningEntities);
        var categoryLearningDTOs = sumByCategories(entitiesGroupedByCategory);

        return new EntireLearningDTO(
                answerHistory.getId(),
                categoryLearningDTOs,
                answerHistory.getTrialExam().toDTO(),
                answerHistory.getGeneralLearning().toDTO());
    }

    private Map<Category, List<CategoryLearningEntity>> groupByCategories(List<CategoryLearningEntity> list) {
        return list.stream()
                .collect(Collectors.groupingBy(CategoryLearningEntity::getCategory));
    }

    private List<CategoryLearningDTO> sumByCategories(Map<Category, List<CategoryLearningEntity>> map) {
        var result = new ArrayList<CategoryLearningDTO>();

        map.forEach((category, categoryLearningEntities) -> {
            Integer totalQuestionsAnswered =
                    categoryLearningEntities.stream()
                            .mapToInt(CategoryLearningEntity::getQuestionsAnswered)
                            .sum();

            Integer totalCorrectAnswers =
                    categoryLearningEntities.stream()
                            .mapToInt(CategoryLearningEntity::getCorrectAnswers)
                            .sum();

            result.add(new CategoryLearningDTO(0L, totalQuestionsAnswered, totalCorrectAnswers, category.enumValue));
        });

        return result;
    }

}
