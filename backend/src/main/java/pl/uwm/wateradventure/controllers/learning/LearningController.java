package pl.uwm.wateradventure.controllers.learning;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.learning.EntireLearningDTO;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningDTO;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningUpdateDTO;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningDTO;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningUpdateDTO;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamDTO;
import pl.uwm.wateradventure.services.learning.LearningFacade;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Answers History and its children Entities
 * @Endpoint: learning
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/learning")
class LearningController {

    private final LearningFacade learningFacade;

    @PutMapping("/{participantCourseId}/general-learning")
    @ResponseStatus(HttpStatus.OK)
    GeneralLearningDTO updateParticipantGeneralLearning(
                                          @PathVariable Long participantCourseId,
                                          @Valid
                                          @RequestBody GeneralLearningUpdateDTO dto) {
        return learningFacade.updateGeneralLearning(participantCourseId, dto).toDTO();
    }

    @PutMapping("/{participantCourseId}/category-learning")
    @ResponseStatus(HttpStatus.OK)
    CategoryLearningDTO updateParticipantCategoryLearning(
                                                          @PathVariable Long participantCourseId,
                                                          @Valid
                                                          @RequestBody CategoryLearningUpdateDTO dto) {
        return learningFacade.updateCategoryLearning(participantCourseId, dto).toDTO();
    }

    @PutMapping("/{participantCourseId}/trial-exam")
    @ResponseStatus(HttpStatus.OK)
    TrialExamDTO updateTrialExamLearning(
                                         @PathVariable Long participantCourseId,
                                         @RequestParam boolean isPassed) {
        return learningFacade.updateTrialExamLearning(participantCourseId, isPassed).toDTO();
    }

    // TODO -  later might need to add ParticipantId for security purposes -
    //         to check if person who sends request is a
    //         participant who wants to check his AnswerHistory
    @GetMapping("/{participantCourseId}/answer-history")
    @ResponseStatus(HttpStatus.OK)
    EntireLearningDTO getAnswerHistoryByParticipantCourseId(@PathVariable Long participantCourseId) {
        return learningFacade.getAnswerHistoryByParticipantCourseId(participantCourseId);
    }


}
