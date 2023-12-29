package pl.uwm.wateradventure.services.questions.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.QuestionEntity;

import java.util.List;

@Repository
interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findAllByCategoryIn(List<Category> categories);

}
