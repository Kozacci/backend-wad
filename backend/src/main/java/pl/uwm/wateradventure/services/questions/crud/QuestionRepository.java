package pl.uwm.wateradventure.services.questions.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.questions.QuestionEntity;

@Repository
interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {



}
