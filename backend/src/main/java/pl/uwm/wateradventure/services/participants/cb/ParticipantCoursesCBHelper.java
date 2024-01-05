package pl.uwm.wateradventure.services.participants.cb;

import jakarta.persistence.criteria.*;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidSortByValueException;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticipantCoursesCBHelper {

    private static final Field[] participantCourseEntityFields = ParticipantCourseEntity.class.getDeclaredFields();
    private static final Field[] courseEntityFields = CourseEntity.class.getDeclaredFields();
    private static final Field[] participantEntityFields = ParticipantEntity.class.getDeclaredFields();

    public static void checkSortByValue(String sortBy) {
        if (sortBy == null) return;
        var courseEntityFieldsNames = Arrays.stream(courseEntityFields).map(Field::getName).toList();
        var participantCourseEntityFieldsNames = Arrays.stream(participantCourseEntityFields).map(Field::getName).toList();
        var participantEntityFieldsNames = Arrays.stream(participantEntityFields).map(Field::getName).toList();
        var allEntitiesFieldNames = new ArrayList<>();
        allEntitiesFieldNames.addAll(courseEntityFieldsNames);
        allEntitiesFieldNames.addAll(participantCourseEntityFieldsNames);
        allEntitiesFieldNames.addAll(participantEntityFieldsNames);

        var isSortByValueEqualToOneEntitiesField = allEntitiesFieldNames.stream()
                        .anyMatch(entityFieldName -> entityFieldName.equals(sortBy));

        if (!isSortByValueEqualToOneEntitiesField) {
            throw new InvalidSortByValueException("Given sortBy value doesn't match any CourseEntity " +
                    "or ParticipantCourseEntity or ParticipantEntity field.");
        }
    }

    public static void addSortBy(String sort,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb,
                                 Root<CourseEntity> course,
                                 Join<CourseEntity, ParticipantCourseEntity> participantsJoin) {
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
        if (sortValueIsEqualToOneOfParticipantCourseEntityFields(sort)) {
            query.orderBy(cb.asc(course.get("participants").get(sort)));
            return;
        }
        if (sortValueIsEqualToOneOfParticipantEntityFields(sort)) {
            query.orderBy(cb.asc(course.get("participants").get("participant").get(sort)));
            return;
        }
        query.orderBy(cb.asc(course.get(sort)));
    }

    private static boolean sortValueIsEqualToOneOfParticipantCourseEntityFields(String sort) {
        return Arrays.stream(participantCourseEntityFields)
                .map(Field::getName)
                .toList()
                .contains(sort);
    }

    private static boolean sortValueIsEqualToOneOfParticipantEntityFields(String sort) {
        return Arrays.stream(participantEntityFields)
                .map(Field::getName)
                .toList()
                .contains(sort);
    }

    public static void addParticipantIdPredicate(CriteriaBuilder cb,
                                                 List<Predicate> predicates,
                                                 Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                                 Long participantId) {
        if (participantId != null) {
            predicates.add(cb.equal(joinParticipantCourse.get("participant").get("id"), participantId));
        }

    }

    public static void addIsPassedPredicate(CriteriaBuilder cb,
                                            Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                            List<Predicate> predicates,
                                            Boolean isPassed) {
        if (isPassed != null) {
            if (isPassed){
                predicates.add(cb.isTrue(joinParticipantCourse.get("isPassed")));
            }
            else {
                predicates.add(cb.isFalse(joinParticipantCourse.get("isPassed")));
            }
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

    public static void addEmailPredicate(CriteriaBuilder cb,
                                            Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                            List<Predicate> predicates,
                                            String email) {
        if (email != null) {
            predicates.add(cb.equal(joinParticipantCourse.get("participant").get("email"), email));
        }
    }

    public static void addLastNamePredicate(CriteriaBuilder cb,
                                            Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                            List<Predicate> predicates,
                                            String lastName) {
        if (lastName != null) {
            predicates.add(cb.equal(joinParticipantCourse.get("participant").get("lastName"), lastName));
        }
    }


    public static void addPhoneNumberPredicate(CriteriaBuilder cb,
                                               Join<CourseEntity, ParticipantCourseEntity> joinParticipantCourse,
                                               List<Predicate> predicates,
                                               String phoneNumber) {
        if (phoneNumber != null) {
            predicates.add(cb.equal(joinParticipantCourse.get("participant").get("phoneNumber"), phoneNumber));
        }
    }
}
