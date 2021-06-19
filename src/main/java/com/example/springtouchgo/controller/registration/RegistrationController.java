package com.example.springtouchgo.controller.registration;


import com.example.springtouchgo.model.registration.RegistrationRequest;
import com.example.springtouchgo.service.registration.ConfirmationTokenService;
import com.example.springtouchgo.service.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
// The class operates as an endpoint for each registration request that has ben received by the client

public class RegistrationController {

    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;


    @PostMapping
    public @ResponseBody
    RegistrationRequest register(@RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);

    }


    @GetMapping(path = "/{token}")
    public @ResponseBody
    String confirmUser(@PathVariable("token") String token) {
        return confirmationTokenService.confirmToken(token);
    }



    // a method to enable



}
