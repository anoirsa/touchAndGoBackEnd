package com.example.springtouchgo.controller.appuser;


import com.example.springtouchgo.model.appuser.AppUser;
import com.example.springtouchgo.model.appuser.Score;
import com.example.springtouchgo.model.appuser.ScoreEntry;
import com.example.springtouchgo.service.appuser.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/user_and_scores")
@CrossOrigin(origins = "*" , allowedHeaders = "*")

@AllArgsConstructor
public class AppUserController {
    private final ScoreService scoreService;

    //Annotation to be added later during the game
   // @PreAuthorize("hasAnyAuthority(user_add)")
    @PostMapping
    public @ResponseBody
    String handleScore(@RequestBody ScoreEntry scoreEntry) {
        return scoreService.handleScore(scoreEntry);
    }


    public
    String addCommentToScore(@RequestBody String comment ,@PathVariable("id") Long id) {
        return scoreService.addCommentToScore(comment, id);
    }

    // annotation to be added later in the game
    //@PreAuthorize()

    @GetMapping(path = "/{username}")
    public @ResponseBody
    List<Score> getScores(@PathVariable String username)  {
        return scoreService.getScoresOfUser(username);
    }








}
