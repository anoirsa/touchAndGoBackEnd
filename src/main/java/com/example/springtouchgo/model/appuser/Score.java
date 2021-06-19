package com.example.springtouchgo.model.appuser;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Score {

    @Id
    @SequenceGenerator(
            name = "score_sequence",
            sequenceName = "score_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "score_sequence"
    )
    // A score must have an ID
    private Long id;
    private Integer scorePoints;
    private LocalDateTime scoreDate;
    private String comment;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public Score(AppUser appUser, Integer scorePoints) {
        this.appUser = appUser;
        this.scorePoints = scorePoints;
    }
}
