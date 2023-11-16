package pl.uwm.wateradventure.services.participants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.courses.dtos.CourseFilterDTO;
import pl.uwm.wateradventure.models.participants.dtos.ParticipantCourseFiltersDTO;
import pl.uwm.wateradventure.models.participants.security.AuthenticationResponse;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantLoginDTO;
import pl.uwm.wateradventure.models.participants.security.dtos.ParticipantRegisterDTO;
import pl.uwm.wateradventure.services.participants.cb.ParticipantCoursesCriteriaBuilder;
import pl.uwm.wateradventure.services.participants.crud.ParticipantCRUDService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipantFacade {

    private final ParticipantCRUDService participantCRUDService;
    private final ParticipantCoursesCriteriaBuilder participantCoursesCriteriaBuilder;

    public AuthenticationResponse register(ParticipantRegisterDTO participantRegisterDTO) {
        return participantCRUDService.register(participantRegisterDTO);
    }

    public AuthenticationResponse login(ParticipantLoginDTO participantLoginDTO) {
        return participantCRUDService.login(participantLoginDTO);
    }

    public List<CourseFilterDTO> getCoursesByParticipant(ParticipantCourseFiltersDTO filters) {
        return participantCoursesCriteriaBuilder.getParticipantCoursesByFilters(filters);
    }

}
