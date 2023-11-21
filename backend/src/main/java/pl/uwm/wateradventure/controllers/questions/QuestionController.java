package pl.uwm.wateradventure.controllers.questions;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFilterDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFiltersDTO;
import pl.uwm.wateradventure.services.questions.QuestionFacade;

import java.util.List;

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
    public QuestionEntityDTO addQuestion(@Valid @RequestBody QuestionCreateUpdateDTO questionCreateDTO) {
        return questionFacade.addQuestion(questionCreateDTO);
    }

    @GetMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionEntityDTO getQuestionById(@PathVariable Long questionId) {
        return questionFacade.getQuestionById(questionId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<QuestionEntityDTO> getAllQuestionsPageable() {
        return questionFacade.getAllQuestionsPageable();
    }

    @GetMapping("/filter-by")
    @ResponseStatus(HttpStatus.OK) // without it response without items is 204 (expected is 200)
    ResponseEntity<List<QuestionFilterDTO>> getQuestionsByFilters(@RequestParam(required = false) Long id,
                                                                  @RequestParam(required = false) String content,
                                                                  @RequestParam(required = false) Category category,
                                                                  @RequestParam(required = false) String sortBy
    ) {
        var filters = new QuestionFiltersDTO(id, content, category, sortBy);
        List<QuestionFilterDTO> filteredQuestions = questionFacade.getQuestionsByFilters(filters);
        return !filteredQuestions.isEmpty() ? ResponseEntity.ok(filteredQuestions) : ResponseEntity.noContent().build();
    }

    @PutMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionEntityDTO updateQuestion(@PathVariable Long questionId, @Valid @RequestBody QuestionCreateUpdateDTO questionUpdateDTO) {
        return questionFacade.updateQuestion(questionId, questionUpdateDTO);
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestionById(@PathVariable Long questionId) {
        questionFacade.deleteQuestionById(questionId);
    }


}
