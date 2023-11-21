package pl.uwm.wateradventure.services.participant_courses.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;

interface ParticipantCourseRepository extends JpaRepository<ParticipantCourseEntity, Long> {

}
