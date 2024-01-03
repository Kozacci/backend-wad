package pl.uwm.wateradventure.services.participant_courses.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.uwm.wateradventure.services.participant_courses.crud.ParticipantCourseTestUtils.getParticipantCourseEntities;

@ExtendWith(MockitoExtension.class)
class ParticipantCourseUpdaterTest {

    private final ParticipantCourseRepository participantCourseRepository = Mockito.mock(ParticipantCourseRepository.class);
    private ParticipantCourseUpdater participantCourseUpdater;
    private static final Logger logger = LoggerFactory.getLogger(ParticipantCourseUpdaterTest.class);

    @BeforeEach
    void setUp() {
        participantCourseUpdater = new ParticipantCourseUpdater(participantCourseRepository);
    }

    @Test
    @Order(3)
    void shouldSetIsPassedTrueForAll() {
        logger.info("Should set is passed true for all entities");
        var entities = getParticipantCourseEntities();
        logger.info("Before using participantCourseUpdater.update method:");
        for (int i = 0; i < entities.size(); i++) {
            logger.info("#{} entity isPassed value:{}", i, entities.get(i).getIsPassed());
        }

        participantCourseUpdater.update(entities, true, false);
        assertThat(entities).allSatisfy(participantCourse -> assertThat(participantCourse.getIsPassed()).isEqualTo(Boolean.TRUE));

        logger.info("After using participantCourseUpdater.update method:");
        for (int i = 0; i < entities.size(); i++) {
            logger.info("#{} entity isPassed value:{}", i, entities.get(i).getIsPassed());
        }
    }

    @Test
    @Order(2)
    void shouldSet_HasAccess_And_IsPaid_TrueForAll() {
        logger.info("Should set hasAccess and isPaid true for all entities");
        var entities = getParticipantCourseEntities();
        logger.info("Before using participantCourseUpdater.update method:");
        for (int i = 0; i < entities.size(); i++) {
            logger.info("#{} entity hasAccess value:{}", i, entities.get(i).getHasAccess());
            logger.info("#{} entity isPaid value:{}", i, entities.get(i).getIsPaid());
        }

        participantCourseUpdater.update(entities, false, true);
        assertThat(entities).allSatisfy(participantCourse -> assertThat(participantCourse.getHasAccess()).isEqualTo(Boolean.TRUE));
        assertThat(entities).allSatisfy(participantCourse -> assertThat(participantCourse.getIsPaid()).isEqualTo(Boolean.TRUE));

        logger.info("After using participantCourseUpdater.update method:");
        for (int i = 0; i < entities.size(); i++) {
            logger.info("#{} entity hasAccess value:{}", i, entities.get(i).getHasAccess());
            logger.info("#{} entity isPaid value:{}", i, entities.get(i).getIsPaid());
        }
    }
}
