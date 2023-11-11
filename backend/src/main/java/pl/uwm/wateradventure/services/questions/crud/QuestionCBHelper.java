package pl.uwm.wateradventure.services.questions.crud;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.QuestionEntity;
import pl.uwm.wateradventure.models.questions.dtos.QuestionFilterDTO;

import java.util.List;

// TODO -- maybe create root CBHelper to inherit it in EntityCBHelper ?
class QuestionCBHelper {


    public static void addSortBy(String sort, CriteriaQuery<QuestionFilterDTO> query,
                                 CriteriaBuilder cb, Root<QuestionEntity> question) {
        if (sort == null) {
            query.orderBy(cb.asc(question.get("id")));
            return;
        }
        if (sort.equals("content") || sort.equals("category")) {
            query.orderBy(cb.asc(question.get(sort)));
        }
    }

    // TODO -- adding number to entity fields instead of using id would be awesome
    public static void addNumberPredicate(CriteriaBuilder cb, Root<QuestionEntity> question,
                                          List<Predicate> predicates, Long id) {
        if (id != null) {
            predicates.add(cb.equal(question.get("id"), id));
        }
    }

    public static void addContentPredicate(CriteriaBuilder cb, Root<QuestionEntity> question,
                                           List<Predicate> predicates, String content) {
        if (content != null) {
            predicates.add(cb.equal(question.get("content"), content));
        }
    }

    public static void addCategoryPredicate(CriteriaBuilder cb, Root<QuestionEntity> question,
                                                   List<Predicate> predicates, Category category) {
        if (category != null) {
            predicates.add(cb.equal(question.get("category"), category));
        }
    }

}
