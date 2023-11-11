package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.CourseCity;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseType;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;

@Component
@RequiredArgsConstructor
class CourseCreator {

    private final CourseRepository courseRepository;

    protected CourseEntityDTO addCourse(CourseCreateUpdateDTO courseCreateDTO) {

        CourseEntity newCourse = new CourseEntity(
                CourseType.getCourseType(courseCreateDTO.courseType()),
                courseCreateDTO.dateFrom(),
                courseCreateDTO.dateTo(),
                CourseCity.getCourseCity(courseCreateDTO.city()),
                courseCreateDTO.maxParticipantsNumber());

        courseRepository.saveAndFlush(newCourse);
        return newCourse.toDTO();
    }

}
