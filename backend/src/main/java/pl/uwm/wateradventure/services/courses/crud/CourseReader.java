package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.courses.CourseEntity;

@Component
@RequiredArgsConstructor
class CourseReader {

    private final CourseRepository repository;

    protected CourseEntity getCourseById(Long courseId) {
        return repository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("course", "Course with id: " + courseId + " does not exist!"));
    }

}
