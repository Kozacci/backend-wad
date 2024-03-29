package pl.uwm.wateradventure.services.participants;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantCourseFilterDTO;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantCourseFiltersDTO;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantEntityDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantUpdateDTO;
import pl.uwm.wateradventure.services.participants.cb.ParticipantCoursesCriteriaBuilder;
import pl.uwm.wateradventure.services.participants.crud.ParticipantCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantFacade {

    private final ParticipantCRUDService participantCRUDService;
    private final ParticipantCoursesCriteriaBuilder participantCoursesCriteriaBuilder;

    public ParticipantEntityDTO getParticipantByEmail(String email) {
        return participantCRUDService.getParticipantByEmail(email).toDTO();
    }

    public ParticipantEntityDTO register(ParticipantRegisterDTO participantRegisterDTO) {
        return participantCRUDService.register(participantRegisterDTO);
    }

    public ResponseEntity<?> login(ParticipantLoginDTO participantLoginDTO,
                                   HttpServletResponse response) {
        return participantCRUDService.login(participantLoginDTO, response);
    }

    public ParticipantEntityDTO updateParticipant(Long participantId, ParticipantUpdateDTO participantUpdateDTO) {
        return participantCRUDService.updateParticipant(participantId, participantUpdateDTO);
    }

    public List<ParticipantCourseFilterDTO> getCoursesByParticipant(ParticipantCourseFiltersDTO filters) {
        return participantCoursesCriteriaBuilder.getParticipantCoursesByFilters(filters);
    }

}
