package pl.uwm.wateradventure.services.learning;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.models.learning.general.GeneralLearningEntity;
import pl.uwm.wateradventure.models.learning.general.dtos.GeneralLearningUpdateDTO;
import pl.uwm.wateradventure.services.learning.answers_history.AnswerHistoryCRUDService;
import pl.uwm.wateradventure.services.learning.general.GeneralLearningCRUDService;
import pl.uwm.wateradventure.services.participant_courses.crud.ParticipantCourseCRUDService;

@Component
@RequiredArgsConstructor
public class LearningFacade {

    private final GeneralLearningCRUDService generalLearningCRUDService;
    private final ParticipantCourseCRUDService participantCourseCRUDService;
    private final AnswerHistoryCRUDService answerHistoryCRUDService;

    public GeneralLearningEntity updateGeneralLearning(Long participantCourseId, GeneralLearningUpdateDTO dto) {
        var participantCourse = participantCourseCRUDService.getParticipantCourseById(participantCourseId);
        var generalLearningToChange = answerHistoryCRUDService.getGeneralLearningByParticipantCourse(participantCourse);
        return generalLearningCRUDService.update(generalLearningToChange, dto);
    }

}
