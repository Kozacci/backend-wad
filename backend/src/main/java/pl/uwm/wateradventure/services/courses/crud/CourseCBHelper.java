package pl.uwm.wateradventure.services.courses.crud;

import jakarta.persistence.criteria.*;
import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseStatus;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilteredDTO;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Class created in the needs of CourseReader class
 */
class CourseCBHelper {


    public static void addSortBy(String sort, CriteriaQuery<CourseFilteredDTO> query, CriteriaBuilder cb, Root<CourseEntity> course) {
        query.orderBy(cb.asc(course.get(Objects.requireNonNullElse(sort, "dateFrom"))));
    }

    public static void addTypePredicate(CriteriaBuilder cb, Root<CourseEntity> course, List<Predicate> predicates, String courseType) {
        if (courseType != null) {
            predicates.add(cb.equal(course.get("type"), CourseType.valueOf(courseType)));
        }
    }

    public static void addStatusPredicate(CriteriaBuilder cb, Root<CourseEntity> course, List<Predicate> predicates, String courseStatus) {
        if (courseStatus != null) {
            predicates.add(cb.equal(course.get("status"), CourseStatus.valueOf(courseStatus)));
        }
    }

    public static void addCityPredicate(CriteriaBuilder cb, Root<CourseEntity> course, List<Predicate> predicates, String courseCity) {
        if (courseCity != null) {
            predicates.add(cb.equal(course.get("city"), CourseCity.valueOf(courseCity)));
        }
    }

    public static void addDateFromPredicate(CriteriaBuilder cb, Root<CourseEntity> course, List<Predicate> predicates, LocalDate dateFrom) {
        if (dateFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(course.get("dateFrom"), dateFrom));
        }
    }

    public static void addDateToPredicate(CriteriaBuilder cb, Root<CourseEntity> course, List<Predicate> predicates, LocalDate dateTo) {
        if (dateTo != null) {
            predicates.add(cb.lessThanOrEqualTo(course.get("dateTo"), dateTo));
        }
    }

    public static Subquery<Long> addRegisteredParticipants(CriteriaBuilder cb, Root<CourseEntity> course,
                                                          CriteriaQuery<CourseFilteredDTO> query) {
        Subquery<Long> sq = query.subquery(Long.class);
        Root<ParticipantCourseEntity> sqRoot = sq.from(ParticipantCourseEntity.class);
        Join<ParticipantCourseEntity, CourseEntity> join = sqRoot.join("course");
        sq.select(cb.count(sqRoot.get("id")));
        sq.where(cb.equal(join, course));
        return sq;
    }

    public static void addParticipantsLimitPredicate(CriteriaBuilder cb, Root<CourseEntity> course, List<Predicate> predicates, Integer participantsLimit) {
        if (participantsLimit != null) {
            predicates.add(cb.lessThanOrEqualTo(course.get("maxParticipantsNumber"), participantsLimit));
        }
    }

}
