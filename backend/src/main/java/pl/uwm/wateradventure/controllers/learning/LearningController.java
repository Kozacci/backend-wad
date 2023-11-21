package pl.uwm.wateradventure.controllers.learning;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.learning.LearningFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/learning")
class LearningController {

    private final LearningFacade learningFacade;


}
