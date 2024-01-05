package pl.uwm.wateradventure.services.courses;

import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.time.LocalDate;

public class CourseTestUtils {

    public static CourseEntity getCourse() {
        return new CourseEntity(
                CourseType.ZEGLARZ_JACHTOWY,
                LocalDate.now().minusDays(2),
                LocalDate.now(),
                CourseCity.OLECKO,
                15
        );
    }

    public static void addParticipantsToCourse(CourseEntity course, int numberOfParticipants) {
        for (int i = 0; i < numberOfParticipants; i++) {
            ParticipantCourseEntity participantCourse = new ParticipantCourseEntity();
            course.getParticipants().add(participantCourse);
        }
    }
}
