package pl.uwm.wateradventure.services.learning;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.learning.crud.LearningCRUDService;

@Component
@RequiredArgsConstructor
public class LearningFacade {

    private final LearningCRUDService learningCRUDService;

}
