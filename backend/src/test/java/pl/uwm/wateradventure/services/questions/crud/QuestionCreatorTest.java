package pl.uwm.wateradventure.services.questions.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.uwm.wateradventure.models.questions.QuestionEntity;
import pl.uwm.wateradventure.models.questions.dtos.QuestionEntityDTO;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.uwm.wateradventure.services.questions.QuestionUtilsTest.getQuestionCreateUpdateDTO;

@ExtendWith(MockitoExtension.class)
class QuestionCreatorTest {

    private final QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    private static final Logger logger = LoggerFactory.getLogger(QuestionCreatorTest.class);
    private QuestionCreator questionCreator;

    @BeforeEach
    void setUp() {
        questionCreator = new QuestionCreator(questionRepository);
    }

    @Test
    void shouldCreateQuestion() {
        logger.info("Should create question");
        var dto = getQuestionCreateUpdateDTO();
        when(questionRepository.saveAndFlush(any(QuestionEntity.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        var result = questionCreator.addQuestion(dto);
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(QuestionEntityDTO.class);
        logger.info("Successful test!");
    }


}
