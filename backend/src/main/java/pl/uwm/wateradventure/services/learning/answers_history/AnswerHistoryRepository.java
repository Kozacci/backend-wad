package pl.uwm.wateradventure.services.learning.answers_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;

import java.util.Optional;

@Repository
interface AnswerHistoryRepository extends JpaRepository<AnswerHistoryEntity, Long> {

    @Query("""
            SELECT p.answerHistory
            FROM ParticipantCourseEntity p
            WHERE p.id = :participantCourseId
            """)
    Optional<AnswerHistoryEntity> getAnswerHistoryByParticipantCourseId(Long participantCourseId);

}
