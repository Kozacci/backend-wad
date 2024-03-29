package pl.uwm.wateradventure.models.participant_courses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;
import pl.uwm.wateradventure.models.participant_courses.dtos.ParticipantCourseEntityDTO;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "participant_courses")
@Getter
@Setter
@NoArgsConstructor
public class ParticipantCourseEntity extends WaterAdventureChangeMetricEntity {

    @Column(name = "access_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime accessDate;

    @Column(name = "has_access")
    private Boolean hasAccess;

    @Column(name = "is_passed")
    private Boolean isPassed;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "online_payment")
    private Boolean onlinePayment;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private ParticipantEntity participant;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistory;

    public ParticipantCourseEntity(CourseEntity course, ParticipantEntity participant) {
        // I used some default date here just not to use null value
        this.accessDate = LocalDateTime.of(2000, 1, 1, 12, 0);
        this.isPassed = false;
        this.isPaid = false;
        this.hasAccess = false;
        // online payment will not be possible in engineering work
        this.onlinePayment = false;
        this.course = course;
        this.participant = participant;
        this.answerHistory = new AnswerHistoryEntity(this);
    }

    public ParticipantCourseEntityDTO toDTO() {
        return ParticipantCourseEntityDTO.builder()
                .participantCourseId(this.id)
                .courseId(this.course.getId())
                .courseType(this.course.getType().enumValue)
                .courseDateFrom(this.course.getDateFrom())
                .courseDateTo(this.course.getDateTo())
                .accessDate(this.accessDate)
                .participantId(this.participant.getId())
                .participantEmail(this.participant.getEmail())
                .participantLastName(this.participant.getLastName())
                .isPassed(this.isPassed)
                .isPaid(this.isPaid)
                .onlinePayment(this.onlinePayment)
                .build();
    }

}
