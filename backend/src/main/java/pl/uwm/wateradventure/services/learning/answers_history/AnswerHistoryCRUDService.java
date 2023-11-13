package pl.uwm.wateradventure.services.learning.answers_history;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

@Service
@RequiredArgsConstructor
public class AnswerHistoryCRUDService {

    private final AnswerHistoryReader reader;

    public GeneralLearningEntity getGeneralLearningByParticipantCourse(ParticipantCourseEntity participantCourse) {
        var answerHistory = reader.getAnswerHistoryByParticipantCourse(participantCourse);
        return answerHistory.getGeneralLearning();
    }

    // if CategoryLearningEntity with such given category doesn't exist on AnswerHistoryEntity connected to
    public CategoryLearningEntity getCategoryLearningByParticipantCourseIdAndCategory(ParticipantCourseEntity participantCourse, Category category) {
        var answerHistory = reader.getAnswerHistoryByParticipantCourse(participantCourse);
        return answerHistory.getCategoryLearningList().stream()
                .filter(categoryLearningEntity -> categoryLearningEntity.getCategory().equals(category))
                .findFirst()
                .orElse(new CategoryLearningEntity(category, answerHistory));
    }

    public TrialExamEntity getTrialExamByParticipantCourseId(ParticipantCourseEntity participantCourse) {
        var answerHistory = reader.getAnswerHistoryByParticipantCourse(participantCourse);
        return answerHistory.getTrialExam();
    }
}
