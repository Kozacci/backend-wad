package pl.uwm.wateradventure.services.courses.crud;

import jakarta.persistence.criteria.*;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidSortByValueException;
import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseStatus;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilterDTO;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


// TODO -- maybe create root CBHelper to inherit it in EntityCBHelper ?
/**
 * Class created in the needs of CourseReader class
 */
public class CourseCBHelper {

    // could be method in EntityCBHelper if it will ever be created
    private static void checkSortByValue(String sortBy) {
        if (sortBy == null) return;
        Field[] courseEntityFields = CourseEntity.class.getDeclaredFields();

        var isSortByValueEqualToOneOfCourseEntityField =
                Arrays.stream(courseEntityFields)
                .anyMatch(field -> field.getName().equals(sortBy));

        if (!isSortByValueEqualToOneOfCourseEntityField) {
            throw new InvalidSortByValueException("Given sortBy value doesn't match any CourseEntity field.");
        }
    }

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

    public static void addTypePredicate(CriteriaBuilder cb, Root<CourseEntity> course,
                                        List<Predicate> predicates, String courseType) {
        if (courseType != null) {
            predicates.add(cb.equal(course.get("type"), CourseType.valueOf(courseType)));
        }
    }

    public static void addStatusPredicate(CriteriaBuilder cb, Root<CourseEntity> course,
                                          List<Predicate> predicates, String courseStatus) {
        if (courseStatus != null) {
            predicates.add(cb.equal(course.get("status"), CourseStatus.valueOf(courseStatus)));
        }
    }

    public static void addCityPredicate(CriteriaBuilder cb, Root<CourseEntity> course,
                                        List<Predicate> predicates, String courseCity) {
        if (courseCity != null) {
            predicates.add(cb.equal(course.get("city"), CourseCity.valueOf(courseCity)));
        }
    }

    public static void addDateFromPredicate(CriteriaBuilder cb, Root<CourseEntity> course,
                                            List<Predicate> predicates, LocalDate dateFrom) {
        if (dateFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(course.get("dateFrom"), dateFrom));
        }
    }

    public static void addDateToPredicate(CriteriaBuilder cb, Root<CourseEntity> course,
                                          List<Predicate> predicates, LocalDate dateTo) {
        if (dateTo != null) {
            predicates.add(cb.lessThanOrEqualTo(course.get("dateTo"), dateTo));
        }
    }

    public static void addRegisteredParticipantsPredicate(CriteriaBuilder cb,
                                                   Root<CourseEntity> course,
                                                   List<Predicate> predicates,
                                                   Integer registeredParticipants) {
        if (registeredParticipants != null) {
            predicates.add(cb.equal(cb.size(course.get("participants")), registeredParticipants));
        }
    }

    public static Subquery<Long> addRegisteredParticipants(CriteriaBuilder cb, Root<CourseEntity> course,
                                                          CriteriaQuery<CourseFilterDTO> query) {
        Subquery<Long> subQuery = query.subquery(Long.class);
        Root<ParticipantCourseEntity> subQueryRoot = subQuery.from(ParticipantCourseEntity.class);
        Join<ParticipantCourseEntity, CourseEntity> join = subQueryRoot.join("course");
        subQuery.select(cb.count(subQueryRoot.get("id")));
        subQuery.where(cb.equal(join, course));
        return subQuery;
    }

    public static void addParticipantsLimitPredicate(CriteriaBuilder cb, Root<CourseEntity> course,
                                                     List<Predicate> predicates, Integer participantsLimit) {
        if (participantsLimit != null) {
            predicates.add(cb.equal(course.get("maxParticipantsNumber"), participantsLimit));
        }
    }

}
