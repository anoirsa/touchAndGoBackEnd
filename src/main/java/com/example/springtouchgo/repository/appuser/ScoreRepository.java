package com.example.springtouchgo.repository.appuser;

import com.example.springtouchgo.model.appuser.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score , Long> {


    @Transactional
    @Modifying
    @Query(value = "SELECT score_points FROM score WHERE app_user_id = ?1",nativeQuery = true)
    List<Integer> findByIds(Long id);
}
