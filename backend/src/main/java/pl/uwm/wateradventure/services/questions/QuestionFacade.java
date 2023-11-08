package pl.uwm.wateradventure.services.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.questions.crud.QuestionCRUDService;

@Component
@RequiredArgsConstructor
public class QuestionFacade {

    private final QuestionCRUDService questionCRUDService;
}
