package pl.uwm.wateradventure.models.learning.trial_exams.dtos;

import lombok.Builder;

public record TrialExamDTO(Long id,
                           Integer total,
                           Integer passed,
                           Integer failed) {

    @Builder public TrialExamDTO {}
}
