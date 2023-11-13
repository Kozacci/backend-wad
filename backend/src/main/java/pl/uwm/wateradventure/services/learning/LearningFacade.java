package pl.uwm.wateradventure.services.learning;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntity;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningUpdateDTO;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningUpdateDTO;
import pl.uwm.wateradventure.models.learning.trial_exams.TrialExamEntity;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamUpdateDTO;
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

    public GeneralLearningEntity updateGeneralLearning(Long participantCourseId, GeneralLearningUpdateDTO dto) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        var generalLearningToChange = answerHistoryCRUDService.getGeneralLearningByParticipantCourse(participantCourse);
        return generalLearningCRUDService.update(generalLearningToChange, dto);
    }

    public CategoryLearningEntity updateCategoryLearning(Long participantCourseId, CategoryLearningUpdateDTO dto) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        var categoryLearningToChange = answerHistoryCRUDService.getCategoryLearningByParticipantCourseAndCategory(participantCourse, Category.getCategory(dto.category()));
        return categoryLearningCRUDService.update(categoryLearningToChange, dto);
    }

    public TrialExamEntity updateTrialExamLearning(Long participantCourseId, TrialExamUpdateDTO dto) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        var trialExamToChange = answerHistoryCRUDService.getTrialExamByParticipantCourse(participantCourse);
        return trialExamCRUDService.update(trialExamToChange, dto);
    }

}
