package pl.uwm.wateradventure.services.learning.catergory.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;

@Repository
interface CategoryLearningRepository extends JpaRepository<CategoryLearningEntity, Long> {



}
