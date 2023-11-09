package pl.uwm.wateradventure.controllers.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.questions.QuestionFacade;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Questions Entity
 * @Endpoint: questions
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
class QuestionController {

    private final QuestionFacade questionFacade;


}
