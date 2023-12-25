package pl.uwm.wateradventure.services.participants.cb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilterDTO;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantCourseFiltersDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.uwm.wateradventure.services.courses.crud.CourseCBHelper.*;
import static pl.uwm.wateradventure.services.participants.cb.ParticipantCoursesCBHelper.*;

@Component
@RequiredArgsConstructor
public class ParticipantCoursesCriteriaBuilder {

    private final EntityManager em;
    private static Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse;

    // TODO - move to reader (or move others from reader to cb package)
    public List<CourseFilterDTO> getParticipantCoursesByFilters(ParticipantCourseFiltersDTO filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CourseFilterDTO> query = cb.createQuery(CourseFilterDTO.class);
        Root<CourseEntity> course = query.from(CourseEntity.class);
        joinParticipantCourse = course.join("participants"); // might need to add JoinType.LEFT to obtain results without assigned participants

        List<Predicate> predicates = new ArrayList<>();

        addTypePredicate(cb, course, predicates, filters.courseType());
        addStatusPredicate(cb, course, predicates, filters.courseStatus());
        addIsPaidPredicate(cb, joinParticipantCourse, predicates, filters.isPaid());
        addIsPassedPredicate(cb, joinParticipantCourse, predicates, filters.isPassed());
        addParticipantIdPredicate(cb, predicates, joinParticipantCourse, filters.participantId());

        query.select(cb.construct(
                CourseFilterDTO.class,
                toSelection(course, query, cb).toArray(new Selection<?>[0])
        ));

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        addSortBy(filters.sortBy(), query, cb, course, joinParticipantCourse);

        return em.createQuery(query).getResultList();
    }

    private List<Selection<?>> toSelection(Root<CourseEntity> root,
                                           CriteriaQuery<CourseFilterDTO> cq,
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
