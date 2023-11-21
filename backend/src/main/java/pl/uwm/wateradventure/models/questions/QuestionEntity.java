package pl.uwm.wateradventure.models.questions;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class QuestionEntity extends WaterAdventureChangeMetricEntity {

    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "first_answer")
    private String firstAnswer;

    @Column(name = "second_answer")
    private String secondAnswer;

    @Column(name = "third_answer")
    private String thirdAnswer;

    @Column(name = "correct_answer")
    @Enumerated(EnumType.STRING)
    private CorrectAnswer correctAnswer;

    private String explanation;

    private String image;


    public QuestionEntity(String content, Category category, String firstAnswer,
                          String secondAnswer, String thirdAnswer, CorrectAnswer correctAnswer,
                          String explanation, String image) {
        this.content = content;
        this.category = category;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.image = image;
    }

    public QuestionEntityDTO toDTO() {
        return QuestionEntityDTO.builder()
                .questionId(this.id)
                .content(this.content)
                .category(this.category.enumValue)
                .firstAnswer(this.firstAnswer)
                .secondAnswer(this.secondAnswer)
                .thirdAnswer(this.thirdAnswer)
                .correctAnswer(this.correctAnswer.enumValue)
                .explanation(this.explanation)
                .image(this.image)
                .build();
    }

}
