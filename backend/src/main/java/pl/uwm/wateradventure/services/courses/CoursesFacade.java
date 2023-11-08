package pl.uwm.wateradventure.services.courses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.services.courses.crud.CoursesCRUDService;

@Component
@RequiredArgsConstructor
public class CoursesFacade {

    private final CoursesCRUDService coursesCRUDService;


}
