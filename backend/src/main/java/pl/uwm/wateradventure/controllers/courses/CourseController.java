package pl.uwm.wateradventure.controllers.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.services.courses.CourseFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
class CourseController {

    private final CourseFacade courseFacade;

    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    CourseEntityDTO getCourseById(@PathVariable Long courseId) {
        return courseFacade.getCourseById(courseId).toDTO();
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCourseById(@PathVariable Long courseId) {
        courseFacade.deleteCourseById(courseId);
    }

}
