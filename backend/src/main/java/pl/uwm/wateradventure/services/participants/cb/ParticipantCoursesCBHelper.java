package pl.uwm.wateradventure.services.participants.cb;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.List;

public class ParticipantCoursesCBHelper {

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
