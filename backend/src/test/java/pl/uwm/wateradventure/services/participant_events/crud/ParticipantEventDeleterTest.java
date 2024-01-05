package pl.uwm.wateradventure.services.participant_events.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EventCancellationTimeoutException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.uwm.wateradventure.services.participant_events.ParticipantEventTestUtils.getParticipantEventEntityWithEventDatePastOneDay;


@ExtendWith(MockitoExtension.class)
class ParticipantEventDeleterTest {

    private final ParticipantEventRepository participantEventRepository = Mockito.mock(ParticipantEventRepository.class);
    private static final Logger logger = LoggerFactory.getLogger(ParticipantEventDeleterTest.class);

    private ParticipantEventDeleter participantEventDeleter;

    @BeforeEach
    void setUp() {
        participantEventDeleter = new ParticipantEventDeleter(participantEventRepository);
    }

    @Test
    void shouldThrowException() {
        logger.info("Should throw EventCancellationTimeoutException");
        var participantEvent = getParticipantEventEntityWithEventDatePastOneDay();
        assertThatThrownBy(() -> participantEventDeleter.deleteAssigningForEvent(participantEvent))
                .isInstanceOf(EventCancellationTimeoutException.class);
        logger.info("Successful test!");
    }


}
