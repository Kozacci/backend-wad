package pl.uwm.wateradventure.services.participants;

import pl.uwm.wateradventure.models.participants.ParticipantEntity;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantUpdateDTO;

public class ParticipantsUtilsTest {

    public static ParticipantEntity getParticipantEntity() {
        return new ParticipantEntity(
                "Jan",
                "Kowalski",
                "kowalski10@email.com",
                "testpass123@#",
                "505940231"
        );
    }

    public static ParticipantUpdateDTO getParticipantUpdateDTO() {
        return new ParticipantUpdateDTO(
                "Sebastian",
                "Nowak",
                "nowy@email.com",
                "passwordTest123",
                "667154628"
        );
    }
}
