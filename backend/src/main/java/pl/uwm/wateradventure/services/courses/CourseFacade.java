package pl.uwm.wateradventure.services.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.services.courses.crud.CourseCRUDService;

@Component
@RequiredArgsConstructor
public class CourseFacade {

    private final CourseCRUDService courseCRUDService;

    public CourseEntity getCourseById(Long courseId) {
        return courseCRUDService.getCourseById(courseId);
    }

    public CourseEntityDTO addCourse(CourseCreateUpdateDTO courseCreateDTO) {
        return courseCRUDService.addCourse(courseCreateDTO);
    }

    public CourseEntityDTO updateCourse(Long courseId, CourseCreateUpdateDTO courseUpdateDTO) {
        return courseCRUDService.updateCourse(courseId, courseUpdateDTO);
    }

    public void deleteCourseById(Long courseId) {
        courseCRUDService.deleteCourseById(courseId);
    }
}
