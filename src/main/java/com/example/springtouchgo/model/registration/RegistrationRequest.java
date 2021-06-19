package com.example.springtouchgo.model.registration;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class RegistrationRequest{
    private final String username;
    private final String password;
    private final String email;
}
