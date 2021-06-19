package com.example.springtouchgo.service.registration;

import com.example.springtouchgo.model.appuser.AppUser;
import com.example.springtouchgo.model.appuser.AppUserRole;
import com.example.springtouchgo.model.registration.RegistrationRequest;
import com.example.springtouchgo.model.registration.ConfirmationToken;
import com.example.springtouchgo.repository.appuser.AppUserRepository;
import com.example.springtouchgo.repository.registration.TokenRepository;
import com.example.springtouchgo.service.ServiceHelpersMethods;
import com.example.springtouchgo.service.email.SendEmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor


public class RegistrationService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    private SendEmailService sendEmailService;

    public RegistrationRequest register(RegistrationRequest registrationRequest) {
        // Utilize the appuser repository to check if the username and the password are already in the database
        boolean usernameExsit = appUserRepository.findByUsername(registrationRequest.getUsername()).isPresent();
        boolean emailPresent = appUserRepository.findByEmail(registrationRequest.getEmail()).isPresent();

        AppUser newUser = new AppUser(registrationRequest.getUsername(),
                passwordEncoder.encode(registrationRequest.getPassword()),
                registrationRequest.getEmail(),
                false, true, true, true, AppUserRole.NORMAL_USER);
        // If the username and email both are not in our database, the request could go through
        if (!usernameExsit || !emailPresent) appUserRepository.save(newUser);
            // Otherwise the request is blocked with an exception
        else throw new IllegalStateException("Username or email already in registered");
        // A confirmation token must be generated for the the account
        ConfirmationToken confirmationToken = new ConfirmationToken(UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), newUser);

        tokenRepository.save(confirmationToken);
        // We prepare for the confirmation link
        String link ="http://localhost:8081/api/v1/registration/"+confirmationToken.getToken();



        String emailToBeSent = ServiceHelpersMethods.buildEmail(newUser.getUsername(), link);
        sendEmailService.sendEmail(newUser.getEmail(),"Confirmation",emailToBeSent);

        return registrationRequest;

    }


}