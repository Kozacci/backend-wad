package pl.uwm.wateradventure.services.courses.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilteredDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFiltersDTO;
import pl.uwm.wateradventure.services.global.PageReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.uwm.wateradventure.services.courses.crud.CourseCBHelper.*;

@Component
@RequiredArgsConstructor
class CourseReader extends PageReader<CourseEntity> {

    private final CourseRepository repository;
    private final EntityManager em;

    protected CourseEntity getCourseById(Long courseId) {
        return repository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("course", "Course with id: " + courseId + " does not exist!"));
    }

    public Page<CourseEntityDTO> getAllCoursesPageable(){
        return super
                .getAllSortedPageable(repository, "dateFrom", false)
                .map(CourseEntity::toDTO);
    }

    public List<CourseFilteredDTO> getCoursesByFilters(CourseFiltersDTO filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CourseFilteredDTO> query = cb.createQuery(CourseFilteredDTO.class);
        Root<CourseEntity> course = query.from(CourseEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        addTypePredicate(cb, course, predicates, filters.courseType());
        addStatusPredicate(cb, course, predicates, filters.courseStatus());
        addCityPredicate(cb, course, predicates, filters.courseCity());
        addDateFromPredicate(cb, course, predicates, filters.dateFrom());
        addDateToPredicate(cb, course, predicates, filters.dateTo());
        addParticipantsLimitPredicate(cb, course, predicates, filters.participantsLimit());

        query.select(cb.construct(
                CourseFilteredDTO.class,
                toSelection(course, query, cb).toArray(new Selection<?>[0])));

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        addSortBy(filters.sortBy(), query, cb, course);

        return em.createQuery(query).getResultList();
    }

    private List<Selection<?>> toSelection(Root<CourseEntity> root,
                                           CriteriaQuery<CourseFilteredDTO> cq,
                                           CriteriaBuilder cb) {
        return Arrays.asList(
                root.get("id"),
                root.get("dateFrom"),
                root.get("dateTo"),
                root.get("status"),
                root.get("maxParticipantsNumber"),
                root.get("city"),
                root.get("type"),
                addRegisteredParticipants(cb, root, cq)
        );
    }

}
