package com.example.springtouchgo.service.registration;


import com.example.springtouchgo.model.appuser.AppUser;
import com.example.springtouchgo.model.registration.ConfirmationToken;
import com.example.springtouchgo.repository.appuser.AppUserRepository;
import com.example.springtouchgo.repository.registration.TokenRepository;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final TokenRepository tokenRepository;
    private final AppUserRepository appUserRepository;

    public String confirmToken(String token) {
        ConfirmationToken tokenFound = tokenRepository.findByToken(token).orElseThrow(
                () -> new IllegalStateException("Token not found")
        );
        //Troubleshoot
        boolean isAfter = LocalDateTime.now().isAfter(tokenFound.getExpiresAt());
        System.out.println("Expires at " + tokenFound.getExpiresAt());
        System.out.println("Time now " + LocalDateTime.now());
        System.out.println("Results are " +  isAfter );

        if (isAfter) throw new IllegalStateException("Token validity has ended");
        AppUser appUser = appUserRepository
                .findByUsername(tokenFound.getAppUser().getUsername()).orElse(null);
        if (appUser.isEnabled()) throw new IllegalStateException("User is already enabled");
        appUserRepository.enableAppUser(appUser.getUsername());
        return "User is enabled";
    }

}
