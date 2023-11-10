package pl.uwm.wateradventure.services.questions.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;

@Service
@RequiredArgsConstructor
public class QuestionCRUDService {

    private final QuestionCreator creator;
    public QuestionEntityDTO addQuestion(QuestionCreateUpdateDTO dto) {
        return creator.addQuestion(dto);
    }

}
