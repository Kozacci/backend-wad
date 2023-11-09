package pl.uwm.wateradventure.controllers.learning;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
