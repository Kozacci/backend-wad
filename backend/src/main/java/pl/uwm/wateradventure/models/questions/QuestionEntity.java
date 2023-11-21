package pl.uwm.wateradventure.models.questions;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.uwm.wateradventure.models.global.WaterAdventureChangeMetricEntity;
import pl.uwm.wateradventure.models.learning.category.Category;

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
    private String correctAnswer; // TODO --> correct answer probably should be Integer (1st,2nd or 3rd answer is correct, not entire fourth answer)

    private String explanation;

    private String image;


}
