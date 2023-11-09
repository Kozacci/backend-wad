package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;

@Component
@RequiredArgsConstructor
class CourseCreator {

    private final CourseRepository courseRepository;

    protected CourseEntityDTO addCourse(CourseCreateDTO courseCreateDTO) {

        CourseEntity newCourse = new CourseEntity(
                courseCreateDTO.courseType(),
                courseCreateDTO.dateFrom(),
                courseCreateDTO.dateTo(),
                courseCreateDTO.city(),
                courseCreateDTO.maxParticipantsNumber());

        courseRepository.save(newCourse);
        return newCourse.toDTO();
    }

}
