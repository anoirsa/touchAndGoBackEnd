package com.example.springtouchgo.service.appuser;


import com.example.springtouchgo.model.appuser.AppUser;
import com.example.springtouchgo.model.appuser.Score;
import com.example.springtouchgo.model.appuser.ScoreEntry;
import com.example.springtouchgo.repository.appuser.AppUserRepository;
import com.example.springtouchgo.repository.appuser.ScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
// This class is to handle any new score given
// A new score has to be assigned to the user who have made that score
public class ScoreService {
    private final AppUserRepository appUserRepository;
    private final ScoreRepository scoreRepository;

    public String handleScore(ScoreEntry scoreEntry) {

        AppUser userTobeAssigned = appUserRepository.findByUsername(scoreEntry.getUsername()).orElseThrow(() ->
                new IllegalStateException("Username is not found"));
        Score newScoreEntry = new Score(userTobeAssigned, scoreEntry.getScore()
                , LocalDateTime.now(),null);
        scoreRepository.save(newScoreEntry);
        //scoreRank.setAppUser(userTobeAssigned);
        return "Score inserted successfully";
    }

    public List<Score> getScoresOfUser(String username) {
        Long userId = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("New such user is found")).getId();
        List<Long> scores = scoreRepository.findByIds(userId);
        List<Score> listOfscores = scores.stream().map(i -> {
            return scoreRepository.findScoreById(i).orElseThrow(() -> new IllegalStateException("Not found"));
        }).sorted((i1, i2) -> i2.getScorePoints().compareTo(i1.getScorePoints()))
                .collect(Collectors.toList());
        return listOfscores;
    }

    public String addCommentToScore(String comment , Long id) {
        scoreRepository.addCommentToScore(comment, id);
        return "Comment added successfully";
    }
}
