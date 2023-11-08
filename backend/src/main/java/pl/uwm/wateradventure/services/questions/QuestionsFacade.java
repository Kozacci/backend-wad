package pl.uwm.wateradventure.services.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.questions.crud.QuestionsCRUDService;

@Component
@RequiredArgsConstructor
public class QuestionsFacade {

    private final QuestionsCRUDService questionsCRUDService;
}
