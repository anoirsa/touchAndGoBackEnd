package com.example.springtouchgo.controller.appuser;


import com.example.springtouchgo.model.appuser.AppUser;
import com.example.springtouchgo.model.appuser.Question;
import com.example.springtouchgo.model.appuser.Score;
import com.example.springtouchgo.model.appuser.ScoreEntry;
import com.example.springtouchgo.repository.appuser.AppUserRepository;
import com.example.springtouchgo.service.appuser.QuestionService;
import com.example.springtouchgo.service.email.SendEmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
@RequestMapping("management/api/v1/data")
@AllArgsConstructor
public class ManagementBase {
    private final AppUserRepository appUserRepository;
    private final SendEmailService sendEmailService;
    private final QuestionService questionService;

/**
    @GetMapping
   // @PreAuthorize("hasAnyAuthority('user_view_all_details')")
    public @ResponseBody
    List<AppUser> getAllUsers() {

        sendEmailService.sendEmail("p","My first matic email" , "Hello Anouar");
        return appUserRepository.findAll();
    }
**/
    @PostMapping
    public @ResponseBody
    Question insertQuestion (@RequestBody Question question) {
        return questionService.insertQuestion(question);
    }

    @GetMapping(path = "/{type}")
    public @ResponseBody
    List<Question> getAllQuestions(@PathVariable("type") String type) {
        System.out.println("The type is "+ type);
        return questionService.getListOfQuestions(type);
    }

    //@PreAuthorize("user_add")


}
