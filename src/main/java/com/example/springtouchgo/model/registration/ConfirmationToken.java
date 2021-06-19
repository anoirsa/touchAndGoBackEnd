package com.example.springtouchgo.model.registration;

import com.example.springtouchgo.model.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class ConfirmationToken {
    public ConfirmationToken(String token,
                             LocalDateTime issuedAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }

    @SequenceGenerator(
            name = "session_sequence",
            sequenceName = "session_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "session_sequence"
    )

    @Id



    private  Long id;
    private  String token;
    private  LocalDateTime issuedAt;
    private  LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(
            name ="app_user_id",
            nullable = false
    )

    private AppUser appUser;



}
