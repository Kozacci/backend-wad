package pl.uwm.wateradventure.controllers.questions;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;
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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionEntityDTO addQuestion(@Valid @RequestBody QuestionCreateUpdateDTO dto) {
        return questionFacade.addQuestion(dto);
    }


}
