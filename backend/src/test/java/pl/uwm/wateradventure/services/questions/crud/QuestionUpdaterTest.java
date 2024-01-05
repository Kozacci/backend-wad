package pl.uwm.wateradventure.services.questions.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.uwm.wateradventure.models.learning.category.Category;
import pl.uwm.wateradventure.models.questions.CorrectAnswer;
import pl.uwm.wateradventure.models.questions.dtos.QuestionCreateUpdateDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.uwm.wateradventure.services.questions.QuestionUtilsTest.getQuestionEntity;

@ExtendWith(MockitoExtension.class)
class QuestionUpdaterTest {

    private final QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    private static final Logger logger = LoggerFactory.getLogger(QuestionUpdaterTest.class);
    private QuestionUpdater questionUpdater;

    @BeforeEach
    void setUp() {
        questionUpdater = new QuestionUpdater(questionRepository);
    }

    @Test
    void shouldUpdateQuestionCorrectAnswerAndCategory() {
        logger.info("Should update question correct answer and category");
        var entity = getQuestionEntity();
        var dto = QuestionCreateUpdateDTO.builder()
                .correctAnswer("Odpowiedź C")
                .category(Category.PODSTAWY_BUDOWY_JACHTOW.enumValue)
                .build();

        logger.info("Before update entity.getCategory: {}", entity.getCategory());
        logger.info("Before update entity.getCorrectAnswer: {}", entity.getCorrectAnswer());
        logger.info("Perform update");
        questionUpdater.updateQuestion(entity, dto);

        assertThat(entity.getCategory()).isEqualTo(Category.PODSTAWY_BUDOWY_JACHTOW);
        assertThat(entity.getCorrectAnswer()).isEqualTo(CorrectAnswer.THIRD_ANSWER);
        logger.info("After update entity.getCategory: {}", entity.getCategory());
        logger.info("After update entity.getCorrectAnswer: {}", entity.getCorrectAnswer());
        logger.info("Successful test!");
    }
}
