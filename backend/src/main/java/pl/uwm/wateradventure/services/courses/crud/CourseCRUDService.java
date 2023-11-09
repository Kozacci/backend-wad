package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;

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

    public CourseEntityDTO updateCourse(Long courseId, CourseCreateUpdateDTO courseUpdateDTO) {
        return updater.updateCourse(courseId, courseUpdateDTO);
    }

    public void deleteCourseById(Long courseId) {
        var course = reader.getCourseById(courseId);
        deleter.deleteCourse(course);
    }
}
