package pl.uwm.wateradventure.models.learning.trial_exams.dtos;

import jakarta.validation.constraints.NotNull;

public record TrialExamUpdateDTO(@NotNull(message = "")
                                 Boolean isPassed) {
}
