package pl.uwm.wateradventure.services.events.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.uwm.wateradventure.exceptions.value_objects_exceptions.*;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.dtos.EventCreateUpdateDTO;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static pl.uwm.wateradventure.services.events.EventTestUtils.addParticipantsToEvent;
import static pl.uwm.wateradventure.services.events.EventTestUtils.getEvent;

@ExtendWith(MockitoExtension.class)
class EventUpdaterTest {

    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventUpdater eventUpdater;
    private EventEntity event;

    @BeforeEach
    void setUp() {
        event = getEvent();
    }

    @Test
    void shouldUpdateEventSuccessfully() {
        // given
        EventCreateUpdateDTO eventCreateUpdateDTO =
                new EventCreateUpdateDTO(
                        "Wieczór panieński",
                        600.50,
                        LocalDateTime.now().plusDays(1),
                        "Sopot",
                        LocalTime.now().plusHours(1),
                        30
                );
        // when
        when(eventRepository.saveAndFlush(event)).thenReturn(event);
        EventEntityDTO eventEntityUpdated = eventUpdater.updateEvent(event, eventCreateUpdateDTO);
        // then
        assertEquals("Wieczór panieński", eventEntityUpdated.type());
        assertEquals(600.50, eventEntityUpdated.cost());
        assertEquals("Sopot", eventEntityUpdated.city());
        assertEquals(30, eventEntityUpdated.maxParticipantsNumber());
    }

    @Test
    void shouldUpdateEventUnsuccessfullyAndThrowInvalidEventTypeException() {
        // given
        EventCreateUpdateDTO eventCreateUpdateDTO =
                new EventCreateUpdateDTO(
                        "Wieczór panieński INVALID",
                        null,
                        null,
                        null,
                        null,
                        null
                );
        // then
        assertThatThrownBy(() -> eventUpdater.updateEvent(event, eventCreateUpdateDTO))
                .isInstanceOf(InvalidEventTypeException.class);
    }

    @Test
    void shouldUpdateEventUnsuccessfullyAndThrowInvalidCostException() {
        // given
        EventCreateUpdateDTO eventCreateUpdateDTO =
                new EventCreateUpdateDTO(
                        null,
                        -50.40,
                        null,
                        null,
                        null,
                        null
                );
        // then
        assertThatThrownBy(() -> eventUpdater.updateEvent(event, eventCreateUpdateDTO))
                .isInstanceOf(InvalidCostException.class);
    }

    @Test
    void shouldUpdateEventUnsuccessfullyAndThrowInvalidDateException() {
        // given
        EventCreateUpdateDTO eventCreateUpdateDTO =
                new EventCreateUpdateDTO(
                        null,
                        null,
                        LocalDateTime.now().minusDays(1),
                        null,
                        null,
                        null
                );
        // then
        assertThatThrownBy(() -> eventUpdater.updateEvent(event, eventCreateUpdateDTO))
                .isInstanceOf(InvalidDateException.class);
    }

    @Test
    void shouldUpdateEventUnsuccessfullyAndThrowInvalidCityException() {
        // given
        EventCreateUpdateDTO eventCreateUpdateDTO =
                new EventCreateUpdateDTO(
                        null,
                        null,
                        null,
                        "Olecko INVALID",
                        null,
                        null
                );
        // then
        assertThatThrownBy(() -> eventUpdater.updateEvent(event, eventCreateUpdateDTO))
                .isInstanceOf(InvalidEventCityException.class);
    }

    @Test
    void shouldUpdateEventUnsuccessfullyAndThrowInvalidDateForInvalidDurationException() {
        // given
        EventCreateUpdateDTO eventCreateUpdateDTO =
                new EventCreateUpdateDTO(
                        null,
                        null,
                        null,
                        null,
                        LocalTime.of(0, 50),
                        null
                );
        // then
        assertThatThrownBy(() -> eventUpdater.updateEvent(event, eventCreateUpdateDTO))
                .isInstanceOf(InvalidDateException.class);
    }

    @Test
    void shouldUpdateEventUnsuccessfullyAndThrowInvalidMaxParticipantsValueExceptionException() {
        // given
        EventCreateUpdateDTO eventCreateUpdateDTO =
                new EventCreateUpdateDTO(
                        null,
                        null,
                        null,
                        null,
                        null,
                        9
                );
        // when
        addParticipantsToEvent(event, 10);
        // then
        assertThatThrownBy(() -> eventUpdater.updateEvent(event, eventCreateUpdateDTO))
                .isInstanceOf(InvalidMaxParticipantsValueException.class);
    }

}