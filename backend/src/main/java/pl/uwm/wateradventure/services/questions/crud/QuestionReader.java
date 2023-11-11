package pl.uwm.wateradventure.services.questions.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.questions.QuestionEntity;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFilterDTO;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFiltersDTO;
import pl.uwm.wateradventure.services.global.PageReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.uwm.wateradventure.services.questions.crud.QuestionCBHelper.*;

@Component
@RequiredArgsConstructor
public class QuestionReader extends PageReader<QuestionEntity> {

    private final QuestionRepository questionRepository;
    private final EntityManager em;

    public QuestionEntity getQuestionById(Long questionId) {
        return questionRepository
                .findById(questionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("question", "Question with id: " + questionId + " does not exist.")
                );
    }

    public Page<QuestionEntityDTO> getAllQuestionsPageable() {
        return super
                .getAllSortedPageable(questionRepository, "id", true)
                .map(QuestionEntity::toDTO);
    }

    public List<QuestionFilterDTO> getQuestionsByFilters(QuestionFiltersDTO filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<QuestionFilterDTO> query = cb.createQuery(QuestionFilterDTO.class);
        Root<QuestionEntity> question = query.from(QuestionEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        addNumberPredicate(cb, question, predicates, filters.id());
        addContentPredicate(cb, question, predicates, filters.content());
        addCategoryPredicate(cb, question, predicates, filters.category());

        query.select(
                cb.construct(
                        QuestionFilterDTO.class,
                        toSelection(question).toArray(new Selection<?>[0])
                )
        );

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        addSortBy(filters.sortBy(), query, cb, question);

        return em.createQuery(query).getResultList();
    }

    private List<Selection<?>> toSelection(Root<QuestionEntity> root) {
        return Arrays.asList(
                root.get("id"),
                root.get("content"),
                root.get("category"),
                root.get("firstAnswer"),
                root.get("secondAnswer"),
                root.get("thirdAnswer")
        );
    }
}
