package pl.uwm.wateradventure.controllers.learning;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.learning.EntireLearningDTO;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningEntityDTO;
import pl.uwm.wateradventure.models.learning.category.CategoryLearningUpdateDTO;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningEntityDTO;
import pl.uwm.wateradventure.models.learning.trial_exams.dtos.TrialExamEntityDTO;
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

    @GetMapping("/{participantCourseId}/answer-history")
    @ResponseStatus(HttpStatus.OK)
    EntireLearningDTO getAnswerHistoryByParticipantCourseId(@PathVariable Long participantCourseId) {
        return learningFacade.getAnswerHistoryByParticipantCourseId(participantCourseId);
    }

    @PutMapping("/{participantCourseId}/general-learning")
    @ResponseStatus(HttpStatus.OK)
    GeneralLearningEntityDTO updateGeneralLearning(
            @PathVariable Long participantCourseId,
            @RequestParam Boolean isCorrectAnswer
    ) {
        return learningFacade.updateGeneralLearning(participantCourseId, isCorrectAnswer);
    }

    @PutMapping("/{participantCourseId}/category-learning")
    @ResponseStatus(HttpStatus.OK)
    CategoryLearningEntityDTO updateCategoryLearning(
            @PathVariable Long participantCourseId,
            @Valid @RequestBody CategoryLearningUpdateDTO dto
    ) {
        return learningFacade.updateCategoryLearning(participantCourseId, dto);
    }

    @PutMapping("/{participantCourseId}/trial-exam")
    @ResponseStatus(HttpStatus.OK)
    TrialExamEntityDTO updateTrialExamLearning(
            @PathVariable Long participantCourseId,
            @RequestParam boolean isPassed
    ) {
        return learningFacade.updateTrialExamLearning(participantCourseId, isPassed);
    }



}
