package pl.uwm.wateradventure.controllers.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.services.courses.CourseFacade;

/** REST Controller created in the needs of Create, Read, Update, Delete
 * and more complex operations for Course Entity
 * @Endpoint: courses
 */
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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    CourseEntityDTO addCourse(@RequestBody CourseCreateUpdateDTO courseCreateDTO) {
        return courseFacade.addCourse(courseCreateDTO);
    }

    @PutMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    CourseEntityDTO updateCourse(@PathVariable Long courseId, @RequestBody CourseCreateUpdateDTO courseUpdateDTO) {
        return courseFacade.updateCourse(courseId, courseUpdateDTO);
    }

    @DeleteMapping("/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCourseById(@PathVariable Long courseId) {
        courseFacade.deleteCourseById(courseId);
    }

}
