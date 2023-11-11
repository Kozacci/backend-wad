package pl.uwm.wateradventure.services.questions.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionDeleter {

    private final QuestionRepository questionRepository;

    public void deleteQuestionById(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
