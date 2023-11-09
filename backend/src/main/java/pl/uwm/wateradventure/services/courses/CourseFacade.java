package pl.uwm.wateradventure.services.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.services.courses.crud.CourseCRUDService;

@Component
@RequiredArgsConstructor
public class CourseFacade {

    private final CourseCRUDService courseCRUDService;

    public CourseEntity getCourseById(Long courseId) {
        return courseCRUDService.getCourseById(courseId);
    }

    public CourseEntityDTO addCourse(CourseCreateDTO courseCreateDTO) {
        return courseCRUDService.addCourse(courseCreateDTO);
    }


    public void deleteCourseById(Long courseId) {
        courseCRUDService.deleteCourseById(courseId);
    }
}
