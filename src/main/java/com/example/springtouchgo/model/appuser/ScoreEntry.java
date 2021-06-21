package com.example.springtouchgo.model.appuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter

public class ScoreEntry {

    private final String username;
    private final Integer score;
    private final LocalDateTime localDateTime;
   // private String comment;
}
