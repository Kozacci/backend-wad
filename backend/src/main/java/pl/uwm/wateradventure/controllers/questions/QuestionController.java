package pl.uwm.wateradventure.controllers.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.questions.QuestionFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
class QuestionController {

    private final QuestionFacade questionFacade;


}
