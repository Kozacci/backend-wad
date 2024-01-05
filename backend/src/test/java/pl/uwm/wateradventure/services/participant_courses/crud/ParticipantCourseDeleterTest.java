package pl.uwm.wateradventure.services.participant_courses.crud;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uwm.wateradventure.exceptions.custom_exceptions.CourseCancellationTimeoutException;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static pl.uwm.wateradventure.services.participant_courses.ParticipantCourseTestUtils.getParticipantCourseEntities;

@ExtendWith(MockitoExtension.class)
class ParticipantCourseDeleterTest {

    @Mock
    private ParticipantCourseRepository participantCourseRepository;
    @Mock
    ParticipantCourseEntity participantCourseEntity;
    @InjectMocks
    private ParticipantCourseDeleter participantCourseDeleter;

    @Test
    void shouldDeleteAssigningForCourseSuccessfully() {
        participantCourseEntity = getParticipantCourseEntities().get(0);
        participantCourseDeleter.deleteAssigningForCourse(participantCourseEntity);
        verify(participantCourseRepository).delete(participantCourseEntity);
    }

    @Test
    void shouldDeleteAssigningForCourseUnsuccessfullyWithCourseCancellationTimeoutException() {
        participantCourseEntity = getParticipantCourseEntities().get(2);
        assertThatThrownBy(() -> participantCourseDeleter.deleteAssigningForCourse(participantCourseEntity))
                .isInstanceOf(CourseCancellationTimeoutException.class);
    }
}