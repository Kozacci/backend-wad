package pl.uwm.wateradventure.models.participants.dtos;

import lombok.Builder;
import pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseEntityDTO;
import pl.uwm.wateradventure.models.participants.Role;

import java.util.Date;
import java.util.List;

public record ParticipantEntityDTO(Long id,
                                   Date createdAt,
                                   String firstName,
                                   String lastName,
                                   String email,
                                   String password,
                                   String phoneNumber,
                                   List<ParticipantCourseEntityDTO> courses,
                                   Role role) {

    @Builder
    public ParticipantEntityDTO {};
}
