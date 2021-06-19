package com.example.springtouchgo.repository.appuser;

import com.example.springtouchgo.model.appuser.Question;
import com.example.springtouchgo.model.appuser.QuestionLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question , Long> {


    List<Question> findQuestionByQuestionLevel(QuestionLevel level);

}
