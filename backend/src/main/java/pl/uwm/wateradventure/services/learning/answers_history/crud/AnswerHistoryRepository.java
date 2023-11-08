package pl.uwm.wateradventure.services.learning.answers_history.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;

@Repository
interface AnswerHistoryRepository extends JpaRepository<AnswerHistoryEntity, Long> {



}
