package pl.uwm.wateradventure.models.participant_courses;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.courses.CourseEntity;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.learning.answershistory.AnswerHistoryEntity;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

import java.util.Date;

@Entity
@Table(name = "participant_courses")
@Getter
@Setter
@NoArgsConstructor
public class ParticipantCourseEntity extends WaterAdventureChangeMetricEntity {

    @Column(name = "access_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accessDate;

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

    @OneToOne
    @JoinColumn(name = "answers_history_id", referencedColumnName = "id")
    private AnswerHistoryEntity answerHistory;

}
