package pl.uwm.wateradventure.services.learning.general.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;

@Repository
interface GeneralLearningRepository extends JpaRepository<GeneralLearningEntity, Long> {



}
