package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseStatus;

import java.time.LocalDate;
import java.util.List;

/** Service Scheduler created in the needs of
 * updating course status of Course Entity
 * @Scheduled-on: 9:00
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CourseScheduler {

    private final CourseRepository courseRepository;

    @Scheduled(cron = "0 0 9 * * ?")
    public void updateCourseStatuses() {
        List<CourseEntity> courses = courseRepository.findAll();
        courses.forEach(this::updateStatus);
        log.info("Updating all Courses status :");
        courseRepository.saveAll(courses);
    }

    private void updateStatus(CourseEntity course) {
        LocalDate now = LocalDate.now();
        if (now.isBefore(course.getDateFrom())) {
            course.setStatus(CourseStatus.NIEROZPOCZETY);
        } else if (now.isAfter(course.getDateTo())) {
            course.setStatus(CourseStatus.ZAKONCZONY);
        } else {
            course.setStatus(CourseStatus.ROZPOCZETY);
        }
    }

}
