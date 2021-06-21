package com.example.springtouchgo.repository.appuser;

import com.example.springtouchgo.model.appuser.Score;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score , Long> {

    Optional<Score> findScoreById(Long id);

    @Transactional
    @Modifying
    @Query(value = "SELECT id FROM score WHERE app_user_id = ?1",nativeQuery = true)
    List<Long> findByIds(Long id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE score SET comment = ?1 WHERE id = ?2 ", nativeQuery = true)
    void addCommentToScore(String comment, Long id);

}
