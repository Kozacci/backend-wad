package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

@Service
@RequiredArgsConstructor
public class ParticipantCourseCRUDService {

    private final ParticipantCourseReader reader;

    public ParticipantCourseEntity getParticipantCourseById(Long participantCourseId) {
        return reader.getParticipantCourseById(participantCourseId);
    }

}
