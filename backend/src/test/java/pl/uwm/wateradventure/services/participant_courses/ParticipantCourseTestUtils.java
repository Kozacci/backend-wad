package pl.uwm.wateradventure.services.participant_courses;

import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

import java.time.LocalDate;
import java.util.List;

public class ParticipantCourseTestUtils {

    public static List<ParticipantCourseEntity> getParticipantCourseEntities() {
        var first = new ParticipantCourseEntity(
                new CourseEntity(
                        CourseType.MOTOROWODNY_STERNIK_MORSKI,
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 1, 7),
                        CourseCity.SOPOT,
                        15),
                new ParticipantEntity(
                        "Jan",
                        "Kowalski",
                        "jan10@email.com",
                        "passwordtest",
                        "505404303"));

        var second = new ParticipantCourseEntity(
                new CourseEntity(
                        CourseType.MOTOROWODNY_STERNIK_MORSKI,
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 1, 7),
                        CourseCity.SOPOT,
                        15),
                new ParticipantEntity(
                        "Piotr",
                        "Nowak",
                        "piotr20@email.com",
                        "passwordtest",
                        "707808202"));

        var third = new ParticipantCourseEntity(
                new CourseEntity(
                        CourseType.MOTOROWODNY_STERNIK_MORSKI,
                        LocalDate.of(2024, 1, 1),
                        LocalDate.of(2024, 1, 7),
                        CourseCity.SOPOT,
                        15),
                new ParticipantEntity(
                        "Andrzej",
                        "Frontalski",
                        "andrzej55@email.com",
                        "passwordtest",
                        "901454223"));

        first.getParticipant().setId(1L);
        second.getParticipant().setId(2L);
        third.getParticipant().setId(3L);
        third.setIsPaid(true);

        return List.of(first, second, third);
    }
}
