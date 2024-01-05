package pl.uwm.wateradventure.services.participants.crud;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ParticipantCreatorTest {

    @Autowired
    private ParticipantCreator participantCreator;
    @Autowired
    private ParticipantReader participantReader;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void checkIfParticipantIsSuccessfullyRegisteredAndHasEncodedPassword() {
        // given
        String email = "emailTest@op.pl";
        String rawPassword = "rawPassword123";
        var participantToRegister = participantCreator
                .register(
                        ParticipantRegisterDTO
                                .builder()
                                .email(email)
                                .password(rawPassword)
                                .firstName("Test")
                                .lastName("Test")
                                .phoneNumber("123456789")
                                .build()
                );
        var registeredParticipant = participantReader.getParticipantByEmail(participantToRegister.email());

        // when
        Boolean isPasswordEncoded = passwordEncoder.matches(rawPassword, registeredParticipant.getPassword());

        // then
        assertEquals(isPasswordEncoded, true);
    }
}