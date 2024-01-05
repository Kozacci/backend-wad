package pl.uwm.wateradventure.services.participant_courses.crud;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uwm.wateradventure.exceptions.custom_exceptions.MaxParticipantsNumberExceededException;
import pl.uwm.wateradventure.exceptions.custom_exceptions.ParticipantAlreadySignedInException;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.InvalidCourseStatusException;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.courses.CourseStatus;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.uwm.wateradventure.services.participant_courses.ParticipantCourseTestUtils.getParticipantCourseEntities;

@ExtendWith(MockitoExtension.class)
class ParticipantCourseCreatorTest {

    @Mock
    private ParticipantEntity participant;
    @Mock
    private CourseEntity course;
    @Mock
    private List<ParticipantCourseEntity> participantCourses = new ArrayList<>();
    @Mock
    private ParticipantCourseRepository participantCourseRepository;
    @InjectMocks
    private ParticipantCourseCreator participantCourseCreator;

    @Test
    void shouldSignInSuccessfully() {
        // when
        when(course.getParticipants()).thenReturn(participantCourses);
        when(course.getMaxParticipantsNumber()).thenReturn(10);
        when(course.getStatus()).thenReturn(CourseStatus.NIEROZPOCZETY);
        when(participantCourseRepository.saveAndFlush(any(ParticipantCourseEntity.class)))
                .thenReturn(new ParticipantCourseEntity(course, participant));
        ParticipantCourseEntity result = participantCourseCreator.signIn(participant, course);
        // then
        assertNotNull(result);
        verify(participantCourseRepository).saveAndFlush(any(ParticipantCourseEntity.class));
    }

    @Test
    void shouldSignInUnSuccessfullyWithMaxParticipantsNumberException() {
        // given
        participantCourses = getParticipantCourseEntities();
        // when
        when(course.getParticipants()).thenReturn(participantCourses);
        when(course.getMaxParticipantsNumber()).thenReturn(3);
        // then
        assertThrows(
                MaxParticipantsNumberExceededException.class,
                () -> participantCourseCreator.signIn(participant, course)
        );
    }

    @Test
    void shouldSignInUnSuccessfullyWithInvalidCourseStatusException() {
        // when
        when(course.getStatus()).thenReturn(CourseStatus.ROZPOCZETY);
        when(course.getMaxParticipantsNumber()).thenReturn(10);
        // then
        assertThrows(
                InvalidCourseStatusException.class,
                () -> participantCourseCreator.signIn(participant, course)
        );
    }

    @Test
    void shouldSignInUnSuccessfullyWithParticipantAlreadySignedInException() {
        // given
        participantCourses = getParticipantCourseEntities();
        // when
        when(participant.getId()).thenReturn(1L);
        when(course.getStatus()).thenReturn(CourseStatus.NIEROZPOCZETY);
        when(course.getParticipants()).thenReturn(participantCourses);
        when(course.getMaxParticipantsNumber()).thenReturn(10);
        // then
        assertThrows(
                ParticipantAlreadySignedInException.class,
                () -> participantCourseCreator.signIn(participant, course)
        );
    }
}