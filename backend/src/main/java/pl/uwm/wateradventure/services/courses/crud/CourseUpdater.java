package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidDateException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidMaxParticipantsValueException;
import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class CourseUpdater {

    private final CourseRepository courseRepository;

    protected CourseEntityDTO updateCourse(CourseEntity courseToUpdate, CourseCreateUpdateDTO courseUpdateDTO) {
        // TODO: tutaj testy
        checkType(courseToUpdate, courseUpdateDTO.courseType());
        checkDateFromAndDateTo(courseToUpdate, courseUpdateDTO.dateFrom(), courseUpdateDTO.dateTo());
        checkCity(courseToUpdate, courseUpdateDTO.city());
        checkMaxParticipantNumber(courseToUpdate, courseUpdateDTO.maxParticipantsNumber());
        courseRepository.saveAndFlush(courseToUpdate);
        return courseToUpdate.toDTO();
    }

    private void checkType(CourseEntity course, String courseType) {
        if (courseType == null) return;
        var newCourseType = CourseType.getCourseType(courseType);
        course.setType(newCourseType);
    }

    private void checkDateFromAndDateTo(CourseEntity course, LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null && dateTo == null) return;

        if (dateFrom != null) {
            if (dateFrom.isAfter(course.getDateTo()) && (dateTo == null || dateFrom.isAfter(dateTo))) {
                throw new InvalidDateException("Date from", "'Date from' can't be after 'date to'.");
            }
            course.setDateFrom(dateFrom);
        }

        if (dateTo != null) {
            if (dateTo.isBefore(course.getDateFrom()) && (dateFrom == null || dateTo.isAfter(dateFrom))) {
                throw new InvalidDateException("Date to", "'Date to' can't be before 'date from'.");
            }
            course.setDateTo(dateTo);
        }
    }

    private void checkCity(CourseEntity course, String city) {
        if (city == null) return;

        var newCourseCity = CourseCity.getCourseCity(city);
        course.setCity(newCourseCity);
    }

    private void checkMaxParticipantNumber(CourseEntity course, Integer maxParticipantNumber) {
        if (maxParticipantNumber == null) return;

        var amountOfAssignedParticipants = course.getParticipants().size();

        if (amountOfAssignedParticipants > maxParticipantNumber) {
            throw new InvalidMaxParticipantsValueException(
                            "You can't change number of maximum participants for " + maxParticipantNumber +
                            ", because there are already " + amountOfAssignedParticipants + " " +
                            "participants assigned to this course.");
        }

        course.setMaxParticipantsNumber(maxParticipantNumber);
    }

}
