package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;

@Service
@RequiredArgsConstructor
public class CourseCRUDService {

    private final CourseReader reader;
    private final CourseCreator creator;
    private final CourseDeleter deleter;

    public CourseEntity getCourseById(Long courseId) {
        return reader.getCourseById(courseId);
    }

    public CourseEntityDTO addCourse(CourseCreateDTO courseCreateDTO) {
        return creator.addCourse(courseCreateDTO);
    }

    public void deleteCourseById(Long courseId) {
        var course = reader.getCourseById(courseId);
        deleter.deleteCourse(course);
    }
}
