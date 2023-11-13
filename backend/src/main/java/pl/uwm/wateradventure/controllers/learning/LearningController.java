package pl.uwm.wateradventure.controllers.learning;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningDTO;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningUpdateDTO;
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

    @PutMapping("/{participantCourseId}/")
    @ResponseStatus(HttpStatus.OK)
    GeneralLearningDTO updateParticipantGeneralLearning(
                                          @PathVariable Long participantCourseId,
                                          @Valid
                                          @RequestBody GeneralLearningUpdateDTO dto) {
        return learningFacade.updateGeneralLearning(participantCourseId, dto).toDTO();
    }


}
