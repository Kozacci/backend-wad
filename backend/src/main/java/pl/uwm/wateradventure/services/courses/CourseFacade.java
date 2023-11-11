package pl.uwm.wateradventure.services.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilterDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFiltersDTO;
import pl.uwm.wateradventure.services.courses.crud.CourseCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseFacade {

    private final CourseCRUDService courseCRUDService;

    public CourseEntityDTO addCourse(CourseCreateUpdateDTO courseCreateDTO) {
        return courseCRUDService.addCourse(courseCreateDTO);
    }

    public CourseEntity getCourseById(Long courseId) {
        return courseCRUDService.getCourseById(courseId);
    }

    public Page<CourseEntityDTO> getAllCoursesPageable() {
        return courseCRUDService.getAllCoursesPageable();
    }

    public List<CourseFilterDTO> getCoursesByFilters(CourseFiltersDTO filters) {
        return courseCRUDService.getCoursesByFilters(filters);
    }

    public CourseEntityDTO updateCourse(Long courseId, CourseCreateUpdateDTO courseUpdateDTO) {
        return courseCRUDService.updateCourse(courseId, courseUpdateDTO);
    }

    public void deleteCourseById(Long courseId) {
        courseCRUDService.deleteCourseById(courseId);
    }

}
