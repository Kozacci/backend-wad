package pl.uwm.wateradventure.services.participants.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.uwm.wateradventure.services.participants.ParticipantsUtilsTest.getParticipantEntity;
import static pl.uwm.wateradventure.services.participants.ParticipantsUtilsTest.getParticipantUpdateDTO;


@ExtendWith(MockitoExtension.class)
class ParticipantUpdaterTest {

    private final ParticipantRepository participantRepository = Mockito.mock(ParticipantRepository.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private static final Logger logger = LoggerFactory.getLogger(ParticipantUpdaterTest.class);
    private ParticipantUpdater participantUpdater;

    @BeforeEach
    void setUp() {
        participantUpdater = new ParticipantUpdater(participantRepository, passwordEncoder);
    }

    @Test
    void shouldUpdateEveryField() {
        logger.info("Should update every field");
        var entity = getParticipantEntity();
        var updateDTO = getParticipantUpdateDTO();
        logger.info("Perform update");
        participantUpdater.updateParticipant(entity, updateDTO);
        assertThat(entity.getFirstName()).isEqualTo(updateDTO.getFirstName());
        assertThat(entity.getLastName()).isEqualTo(updateDTO.getLastName());
        assertThat(entity.getEmail()).isEqualTo(updateDTO.getEmail());
        assertThat(entity.getPhoneNumber()).isEqualTo(updateDTO.getPhoneNumber());
        logger.info("Successful test!");
    }

    @Test
    void shouldUpdateOnlyNonNullFields() {
        logger.info("Should update only non null fields");
        var entity = getParticipantEntity();
        var updateDTO = getParticipantUpdateDTO();
        logger.info("Setting updateDTO lastName and email = null");
        updateDTO.setLastName(null);
        updateDTO.setEmail(null);
        logger.info("Perform update");
        participantUpdater.updateParticipant(entity, updateDTO);
        assertThat(entity.getFirstName()).isEqualTo(updateDTO.getFirstName());
        assertThat(entity.getLastName()).isNotEqualTo(updateDTO.getLastName());
        assertThat(entity.getEmail()).isNotEqualTo(updateDTO.getEmail());
        assertThat(entity.getPhoneNumber()).isEqualTo(updateDTO.getPhoneNumber());
        logger.info("Successfull test!");
    }
}
