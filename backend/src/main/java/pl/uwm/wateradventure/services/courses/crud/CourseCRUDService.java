package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.courses.CourseEntity;

@Service
@RequiredArgsConstructor
public class CourseCRUDService {

    private final CourseReader reader;

    public CourseEntity getCourseById(Long courseId) {
        return reader.getCourseById(courseId);
    }

}
