package pl.uwm.wateradventure.services.participant_events.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.uwm.wateradventure.exceptions.custom_exceptions.NotEnoughSeatsForEventException;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.uwm.wateradventure.services.participant_events.ParticipantEventTestUtils.getEvent;
import static pl.uwm.wateradventure.services.participant_events.ParticipantEventTestUtils.getParticipantEventEntityCreateDTOWith;

@ExtendWith(MockitoExtension.class)
class ParticipantEventCreatorTest {

    private static final int FIVE_PARTICIPANTS = 5;
    private static final int FIFTEEN_PARTICIPANTS = 15;
    private final ParticipantEventRepository participantEventRepository = Mockito.mock(ParticipantEventRepository.class);
    private static final Logger logger = LoggerFactory.getLogger(ParticipantEventCreator.class);
    private ParticipantEventCreator participantEventCreator;

    @BeforeEach
    void setUp() {
        participantEventCreator = new ParticipantEventCreator(participantEventRepository);
    }

    @Test
    void shouldCreateParticipantEvent_assignedToGivenEvent() {
        logger.info("Should create participant event assigned to given event");
        var event = getEvent();
        var dto = getParticipantEventEntityCreateDTOWith(FIVE_PARTICIPANTS);

        when(participantEventRepository.saveAndFlush(any(ParticipantEventEntity.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        ParticipantEventEntity result = participantEventCreator.signInForEvent(event, dto);

        assertSame(event, result.getEvent());
        logger.info("Successful test!");
    }

    @Test
    void shouldThrowException() {
        logger.info("Should throw NotEnoughSeatsForEventException");
        var event = getEvent();
        var dto = getParticipantEventEntityCreateDTOWith(FIFTEEN_PARTICIPANTS);

        assertThatThrownBy(() -> participantEventCreator.signInForEvent(event, dto))
                .isInstanceOf(NotEnoughSeatsForEventException.class);

        logger.info("Successful test!");
    }

}
