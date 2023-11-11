package pl.uwm.wateradventure.services.questions.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFilterDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFiltersDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionCRUDService {

    private final QuestionCreator creator;
    private final QuestionReader reader;
    private final QuestionUpdater updater;
    private final QuestionDeleter deleter;

    public QuestionEntityDTO addQuestion(QuestionCreateUpdateDTO questionCreateDTO) {
        return creator.addQuestion(questionCreateDTO);
    }

    public QuestionEntityDTO getQuestionById(Long questionId) {
        return reader.getQuestionById(questionId).toDTO();
    }

    public Page<QuestionEntityDTO> getAllQuestionsPageable() {
        return reader.getAllQuestionsPageable();
    }

    public List<QuestionFilterDTO> getQuestionsByFilters(QuestionFiltersDTO filters) {
        return reader.getQuestionsByFilters(filters);
    }

    public QuestionEntityDTO updateQuestion(Long questionId, QuestionCreateUpdateDTO questionUpdateDTO) {
        return updater.updateQuestion(questionId, questionUpdateDTO);
    }

    public void deleteQuestionById(Long questionId) {
        deleter.deleteQuestionById(questionId);
    }

}
