package pl.uwm.wateradventure.services.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFilterDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFiltersDTO;
import pl.uwm.wateradventure.services.questions.crud.QuestionCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionFacade {

    private final QuestionCRUDService questionCRUDService;

    public QuestionEntityDTO addQuestion(QuestionCreateUpdateDTO questionCreateDTO) {
        return questionCRUDService.addQuestion(questionCreateDTO);
    }

    public QuestionEntityDTO getQuestionById(Long questionId) {
        return questionCRUDService.getQuestionById(questionId);
    }
    public Page<QuestionEntityDTO> getAllQuestionsPageable() {
        return questionCRUDService.getAllQuestionsPageable();
    }

    public QuestionEntityDTO getRandomQuestionByCategories(List<Category> categories) {
        return questionCRUDService.getRandomQuestionByCategories(categories);
    }

    public List<QuestionFilterDTO> getQuestionsByFilters(QuestionFiltersDTO filters) {
        return questionCRUDService.getQuestionsByFilters(filters);
    }

    public QuestionEntityDTO updateQuestion(Long questionId, QuestionCreateUpdateDTO questionUpdateDTO) {
        return questionCRUDService.updateQuestion(questionId, questionUpdateDTO);
    }

    public void deleteQuestionById(Long questionId) {
        questionCRUDService.deleteQuestionById(questionId);
    }
}
