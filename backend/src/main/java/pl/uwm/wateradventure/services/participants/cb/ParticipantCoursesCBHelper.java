package pl.uwm.wateradventure.services.participants.cb;

import jakarta.persistence.criteria.*;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilterDTO;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.List;

import static pl.uwm.wateradventure.services.courses.crud.CourseCBHelper.*;

public class ParticipantCoursesCBHelper {

    public static void addSortBy(String sort, CriteriaQuery<CourseFilterDTO> query,
                                 CriteriaBuilder cb, Root<CourseEntity> course, Join<CourseEntity, ParticipantCourseEntity> participantsJoin) {
        checkSortByValue(sort);
        if (sort == null) {
            query.orderBy(cb.asc(course.get("dateFrom")));
            return;
        }
        if (sort.equals("participants")) {
            Expression<Long> countParticipants = cb.count(participantsJoin.get("id"));
            query.groupBy(course.get("id"));
            query.orderBy(cb.desc(countParticipants));
            return;
        }
        query.orderBy(cb.asc(course.get(sort)));
    }

    public static void addParticipantIdPredicate(CriteriaBuilder cb,
                                                 List<Predicate> predicates,
                                                 Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                                 Long participantId) {
        predicates.add(cb.equal(joinParticipantCourse.get("participant").get("id"), participantId));

    }

    public static void addIsPassedPredicate(CriteriaBuilder cb,
                                            Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                            List<Predicate> predicates,
                                            Boolean isPassed) {
        if (isPassed != null) {
            predicates.add(cb.equal(joinParticipantCourse.get("isPassed"), isPassed));
        }

    }

    public static void addIsPaidPredicate(CriteriaBuilder cb,
                                          Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                          List<Predicate> predicates,
                                          Boolean isPaid) {
        if (isPaid != null) {
            predicates.add(cb.equal(joinParticipantCourse.get("isPaid"), isPaid));
        }
    }
}
