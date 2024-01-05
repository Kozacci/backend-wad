package pl.uwm.wateradventure.services.courses.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseCityException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseTypeException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidDateException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidMaxParticipantsValueException;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.dtos.CourseCreateUpdateDTO;
import pl.uwm.wateradventure.models.courses.dtos.CourseEntityDTO;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.uwm.wateradventure.services.courses.CourseTestUtils.addParticipantsToCourse;
import static pl.uwm.wateradventure.services.courses.CourseTestUtils.getCourse;


@ExtendWith(MockitoExtension.class)
class CourseUpdaterTest {

    private CourseEntity course;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseUpdater courseUpdater;

    @BeforeEach
    void setUp() {
        course = getCourse();
    }

    @Test
    void shouldUpdateCourseWithCourseTypeSuccessfully() {
        // given
        CourseCreateUpdateDTO updateDTO =
                new CourseCreateUpdateDTO(
                        "Sternik motorowodny",
                        LocalDate.now().minusDays(4),
                        LocalDate.now(),
                        "Olecko",
                        15
                );
        // when
        when(courseRepository.saveAndFlush(course)).thenReturn(course);
        CourseEntityDTO updatedCourseDTO = courseUpdater.updateCourse(course, updateDTO);
        // then
        assertEquals("Sternik motorowodny", updatedCourseDTO.courseType());
        verify(courseRepository).saveAndFlush(course);
    }

    @Test
    void shouldUpdateCourseUnsuccessfullyAndThrowInvalidCourseTypeException() {
        // given
        CourseCreateUpdateDTO updateDTO =
                new CourseCreateUpdateDTO(
                        "Sternik motorowodny INVALID",
                        LocalDate.now().minusDays(4),
                        LocalDate.now(),
                        "Olecko",
                        15
                );
        // then
        assertThatThrownBy(() -> courseUpdater.updateCourse(course, updateDTO))
                .isInstanceOf(InvalidCourseTypeException.class);
    }

    @Test
    void shouldUpdateCourseUnsuccessfullyAndThrowInvalidDateException() {
        // given
        CourseCreateUpdateDTO updateDTO =
                new CourseCreateUpdateDTO(
                        "Sternik motorowodny",
                        null,
                        LocalDate.now().minusDays(5),
                        "Olecko",
                        15
                );
        // then
        assertThatThrownBy(() -> courseUpdater.updateCourse(course, updateDTO))
                .isInstanceOf(InvalidDateException.class);
    }

    @Test
    void shouldUpdateCourseUnsuccessfullyAndThrowInvalidCourseCityException() {
        // given
        CourseCreateUpdateDTO updateDTO =
                new CourseCreateUpdateDTO(
                        "Sternik motorowodny",
                        LocalDate.now().minusDays(1),
                        LocalDate.now(),
                        "Olecko INVALID",
                        15
                );
        // then
        assertThatThrownBy(() -> courseUpdater.updateCourse(course, updateDTO))
                .isInstanceOf(InvalidCourseCityException.class);
    }

    @Test
    void updateCourseWithInvalidMaxParticipantsThrowsException() {
        // given
        CourseCreateUpdateDTO updateDTO =
                new CourseCreateUpdateDTO(
                        "Sternik motorowodny",
                        LocalDate.now().minusDays(1),
                        LocalDate.now(),
                        "Olecko",
                        1
                );
        // when
        addParticipantsToCourse(course, 2);
        // then
        assertThatThrownBy(() -> courseUpdater.updateCourse(course, updateDTO))
                .isInstanceOf(InvalidMaxParticipantsValueException.class);
    }

}