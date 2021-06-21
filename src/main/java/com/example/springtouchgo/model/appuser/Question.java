package com.example.springtouchgo.model.appuser;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity

public class Question {

    @SequenceGenerator(
          name = "question_sequence",
          sequenceName = "question_seqeunce",
          allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )

    @Id
    private Long id;
    private String questionText;
    private String option1, option2, option3, option4;
    private String correctAnswer;
    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private QuestionLevel questionLevel;

    public Question(String questionText,
                    String option1, String option2
                   , String option3, String option4,
                    QuestionLevel questionLevel,
                    Category category,
                    String correctAnswer) {
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        this.questionLevel = questionLevel;
        this.category = category;
    }
}
