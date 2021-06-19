package com.example.springtouchgo.service.appuser;


import com.example.springtouchgo.model.appuser.AppUser;
import com.example.springtouchgo.model.appuser.Score;
import com.example.springtouchgo.repository.appuser.AppUserRepository;
import com.example.springtouchgo.repository.appuser.ScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
// This class is to handle any new score given
// A new score has to be assigned to the user who have made that score
public class ScoreService {
    private final AppUserRepository appUserRepository;
    private final ScoreRepository scoreRepository;

    public String handleScore(String username , Integer scoreEntry) {

        AppUser userTobeAssigned = appUserRepository.findByUsername(username).orElseThrow(() ->
                new IllegalStateException("Username is not found"));
        Score newScoreEntry = new Score(userTobeAssigned, scoreEntry);
        scoreRepository.save(newScoreEntry);
        //scoreRank.setAppUser(userTobeAssigned);
        return "Score inserted successfully";
    }

    public List<Integer> getScoresOfUser(String username) {
        Long userId = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("New such user is found")).getId();
        List<Integer> scores = scoreRepository.findByIds(userId);
        return scores;

    }
}
