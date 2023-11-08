package pl.uwm.wateradventure.controllers.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.uwm.wateradventure.services.courses.CoursesFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
class CoursesController {

    private final CoursesFacade coursesFacade;
}
