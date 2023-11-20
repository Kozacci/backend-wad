package pl.uwm.wateradventure.services.participant_courses.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import pl.uwm.wateradventure.models.participant_courses.ParticipantCourseEntity;
import pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseEntityDTO;

import java.util.List;

interface ParticipantCourseRepository extends JpaRepository<ParticipantCourseEntity, Long> {

    @Query("""
            SELECT new pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseEntityDTO
            (
                ce.id,
                CAST(ce.type as text),
                ce.dateFrom,
                ce.dateTo,
                pce.accessDate,
                pe.id,
                pe.email,
                pe.lastName,
                pce.isPassed,
                pce.isPaid,
                pce.onlinePayment
            )
            FROM ParticipantCourseEntity pce
            JOIN ParticipantEntity pe on pce.participant.id = :participantId
            JOIN CourseEntity ce on ce.id = pce.course.id
            WHERE pe.id = :participantId
           """)
    List<ParticipantCourseEntityDTO> getParticipantCoursesByParticipantId(@Param("participantId") Long participantId);
}
