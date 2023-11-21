package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.CourseEntity;

@Component
@RequiredArgsConstructor
class CourseDeleter {

    private final CourseRepository repository;

    public void deleteCourse(CourseEntity course) {
        repository.delete(course);
    }

}
