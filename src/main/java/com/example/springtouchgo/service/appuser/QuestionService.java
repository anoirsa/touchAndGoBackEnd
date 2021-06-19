package com.example.springtouchgo.service.appuser;

import com.example.springtouchgo.model.appuser.Question;
import com.example.springtouchgo.model.appuser.QuestionLevel;
import com.example.springtouchgo.repository.appuser.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;


    public Question insertQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getListOfQuestions(String questionType) {
        QuestionLevel questionLevel = QuestionLevel.valueOf(questionType);
        List<Question> questionByQuestionLevel = questionRepository.findQuestionByQuestionLevel(questionLevel);
        System.out.println("Testing of : ");
        System.out.println(questionByQuestionLevel);
        Collections.shuffle(questionByQuestionLevel);
        List<Question> randomQuestions = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++ ) randomQuestions.add(questionByQuestionLevel.get(i));
        return randomQuestions;
    }

}
