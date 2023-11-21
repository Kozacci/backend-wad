package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilterDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFiltersDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseCRUDService {

    private final CourseCreator creator;
    private final CourseReader reader;
    private final CourseUpdater updater;
    private final CourseDeleter deleter;

    public CourseEntityDTO addCourse(CourseCreateUpdateDTO courseCreateDTO) {
        return creator.addCourse(courseCreateDTO);
    }

    public CourseEntity getCourseById(Long courseId) {
        return reader.getCourseById(courseId);
    }

    public Page<CourseEntityDTO> getAllCoursesPageable() {
        return reader.getAllCoursesPageable();
    }

    public CourseEntityDTO updateCourse(Long courseId, CourseCreateUpdateDTO courseUpdateDTO) {
        var courseToUpdate = reader.getCourseById(courseId);
        return updater.updateCourse(courseToUpdate, courseUpdateDTO);
    }

    public void deleteCourseById(Long courseId) {
        var course = reader.getCourseById(courseId);
        deleter.deleteCourse(course);
    }

    public List<CourseFilterDTO> getCoursesByFilters(CourseFiltersDTO filters) {
        return reader.getCoursesByFilters(filters);
    }
}
