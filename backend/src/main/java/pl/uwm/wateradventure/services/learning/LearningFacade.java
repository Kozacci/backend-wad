package pl.uwm.wateradventure.services.learning;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.EntireLearningDTO;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntityDTO;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningUpdateDTO;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningEntityDTO;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamEntityDTO;
import pl.uwm.wateradventure.services.learning.answers_history.AnswerHistoryCRUDService;
import pl.uwm.wateradventure.services.learning.catergory.CategoryLearningCRUDService;
import pl.uwm.wateradventure.services.learning.general.GeneralLearningCRUDService;
import pl.uwm.wateradventure.services.learning.trial_exams.TrialExamCRUDService;
import pl.uwm.wateradventure.services.participant_courses.crud.ParticipantCourseCRUDService;

@Component
@RequiredArgsConstructor
public class LearningFacade {

    private final GeneralLearningCRUDService generalLearningCRUDService;
    private final ParticipantCourseCRUDService participantCourseCRUDService;
    private final AnswerHistoryCRUDService answerHistoryCRUDService;
    private final CategoryLearningCRUDService categoryLearningCRUDService;
    private final TrialExamCRUDService trialExamCRUDService;
    private final LearningCounter counter;

    public GeneralLearningEntityDTO updateGeneralLearning(
            Long participantCourseId,
            Boolean isCorrectAnswer
    ) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        var generalLearningToChange = answerHistoryCRUDService.getGeneralLearningByParticipantCourse(participantCourse);
        return generalLearningCRUDService
                .update(generalLearningToChange, isCorrectAnswer)
                .toDTO();
    }

    public CategoryLearningEntityDTO updateCategoryLearning(Long participantCourseId, CategoryLearningUpdateDTO dto) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        var categoryLearningToChange = answerHistoryCRUDService.getCategoryLearningByParticipantCourseAndCategory(participantCourse, Category.getCategory(dto.category()));
        return categoryLearningCRUDService.update(categoryLearningToChange, dto).toDTO();
    }

    public TrialExamEntityDTO updateTrialExamLearning(Long participantCourseId, Boolean isPassed) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        var trialExamToChange = answerHistoryCRUDService.getTrialExamByParticipantCourse(participantCourse);
        return trialExamCRUDService.update(trialExamToChange, isPassed).toDTO();
    }

    // later might need to add ParticipantId for security purposes - to check if person who sends request is a
    // participant who wants to check his LearningHistory
    public EntireLearningDTO getAnswerHistoryByParticipantCourseId(Long participantCourseId) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        return counter.countAllAnswers(participantCourse.getAnswerHistory());
    }

}
