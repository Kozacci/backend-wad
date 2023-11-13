package pl.uwm.wateradventure.services.learning.trial_exams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;

@Repository
interface TrialExamRepository extends JpaRepository<TrialExamEntity, Long> {
}
