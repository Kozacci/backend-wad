package pl.uwm.wateradventure.services.courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;

@Component
@RequiredArgsConstructor
class CourseUpdater {

    private final CourseRepository courseRepository;
    private final CourseReader courseReader;

    protected CourseEntityDTO updateCourse(Long courseId, CourseCreateUpdateDTO courseUpdateDTO) {
        var courseToUpdate = courseReader.getCourseById(courseId);
        courseToUpdate.setType(courseUpdateDTO.courseType());
        courseToUpdate.setDateFrom(courseUpdateDTO.dateFrom());
        courseToUpdate.setDateTo(courseUpdateDTO.dateTo());
        courseToUpdate.setCity(courseUpdateDTO.city());
        courseToUpdate.setMaxParticipantsNumber(courseUpdateDTO.maxParticipantsNumber());
        courseRepository.saveAndFlush(courseToUpdate);
        return courseToUpdate.toDTO();
    }
}
