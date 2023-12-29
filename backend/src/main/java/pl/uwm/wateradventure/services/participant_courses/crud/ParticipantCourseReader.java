package pl.uwm.wateradventure.services.participant_courses.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
class ParticipantCourseReader {

    private final ParticipantCourseRepository repository;

    public ParticipantCourseEntity getParticipantCourseById(Long participantCourseId) {
        return repository.findById(participantCourseId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "participantCourse", "Participant course with id: " + participantCourseId + " does not exist!"
                ));
    }

    public List<ParticipantCourseEntity> getParticipantCourseByIdIn(List<Long> longs) {
        return repository.findAllById(longs);
    }
}
