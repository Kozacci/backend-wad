package pl.uwm.wateradventure.models.questions.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import pl.uwm.wateradventure.models.validators.Category;
import pl.uwm.wateradventure.models.validators.CorrectAnswer;

public record QuestionCreateUpdateDTO(@NotBlank(message = "'Content' field must be filled.")
                                      String content,
                                      @NotNull(message = "Please indicate the section to which the question belongs.")
                                      @Category
                                      String category,
                                      @NotBlank(message = "'Answer A' field must be filled.")
                                      @Size(max = 60, message = "The answer cannot exceed 60 characters.")
                                      String firstAnswer,
                                      @NotBlank(message = "'Answer B' field must be filled.")
                                      @Size(max = 60, message = "The answer cannot exceed 60 characters.")
                                      String secondAnswer,
                                      @NotBlank(message = "'Answer C' field must be filled.")
                                      @Size(max = 60, message = "The answer cannot exceed 60 characters.")
                                      String thirdAnswer,
                                      @NotNull(message = "Please select correct answer.")
                                      @CorrectAnswer
                                      String correctAnswer,
                                      String explanation,
                                      String image
                                      ) {

    @Builder public QuestionCreateUpdateDTO {}
}
