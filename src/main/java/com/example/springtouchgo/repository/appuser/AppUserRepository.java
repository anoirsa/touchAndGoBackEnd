package com.example.springtouchgo.repository.appuser;

import com.example.springtouchgo.model.appuser.AppUser;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser , Long> {
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE app_user SET is_enabled='true' WHERE username = ?1",nativeQuery = true)
    int enableAppUser(String username);
}
