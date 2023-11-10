package pl.uwm.wateradventure.controllers.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilteredDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseFiltersDTO;
import pl.uwm.wateradventure.services.courses.CourseFacade;

import java.time.LocalDate;
import java.util.List;

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

    @GetMapping()
    ResponseEntity<List<CourseFilteredDTO>> getCoursesByFilters(@RequestParam(required = false) String courseType,
                                                               @RequestParam(required = false) String courseStatus,
                                                               @RequestParam(required = false) String courseCity,
                                                               @RequestParam(required = false) LocalDate dateFrom,
                                                               @RequestParam(required = false) LocalDate dateTo,
                                                               @RequestParam(required = false) Integer registeredParticipants,
                                                               @RequestParam(required = false) Integer participantsLimit,
                                                               @RequestParam(required = false) String sortBy) {
        var filters = new CourseFiltersDTO(courseType, courseStatus, courseCity, dateFrom, dateTo,
                            registeredParticipants, participantsLimit, sortBy);
        List<CourseFilteredDTO> filteredCourses = courseFacade.getCoursesByFilters(filters);
        return !filteredCourses.isEmpty() ? ResponseEntity.ok(filteredCourses) : ResponseEntity.noContent().build();
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
