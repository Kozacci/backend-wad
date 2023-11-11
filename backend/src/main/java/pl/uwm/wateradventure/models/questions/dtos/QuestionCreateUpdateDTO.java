package pl.uwm.wateradventure.models.questions.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pl.uwm.wateradventure.models.validators.Category;
import pl.uwm.wateradventure.models.validators.CorrectAnswer;

public record QuestionCreateUpdateDTO(@NotBlank(message = "Pole 'Treść pytania' nie może być puste.")
                                      String content,
                                      @NotNull(message = "Proszę wskazać dział, do którego należy pytanie.")
                                      @Category
                                      String category,
                                      @NotBlank(message = "Pole 'Odpowiedź A' nie może być puste.")
                                      @Size(max = 60, message = "Odpowiedź nie może liczyć więcej niż 60 znaków.")
                                      String firstAnswer,
                                      @NotBlank(message = "Pole 'Odpowiedź B' nie może być puste.")
                                      @Size(max = 60, message = "Odpowiedź nie może liczyć więcej niż 60 znaków.")
                                      String secondAnswer,
                                      @NotBlank(message = "Pole 'Odpowiedź C' nie może być puste.")
                                      @Size(max = 60, message = "Odpowiedź nie może liczyć więcej niż 60 znaków.")
                                      String thirdAnswer,
                                      @NotNull(message = "Proszę wskazać poprawną odpowiedź.")
                                      @CorrectAnswer
                                      String correctAnswer,
                                      String explanation,
                                      String image
                                      ) {
}
