package pl.uwm.wateradventure.models.participants.dtos;

import pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseEntityDTO;
import pl.uwm.wateradventure.models.participants.Role;

import java.util.List;

public record ParticipantEntityDTO(String firstName,
                                   String lastName,
                                   String email,
                                   String password,
                                   String phoneNumber,
                                   List<ParticipantCourseEntityDTO> courses,
                                   Role role) {
}
