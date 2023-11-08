package pl.uwm.wateradventure.controllers.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.questions.QuestionsFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
class QuestionsController {

    private final QuestionsFacade questionsFacade;


}
