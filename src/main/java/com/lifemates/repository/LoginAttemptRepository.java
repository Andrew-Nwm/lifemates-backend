package com.lifemates.repository;

import com.lifemates.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    List<LoginAttempt> findByUsername(String username);
    List<LoginAttempt> findByIpAddress(String ipAddress);
}
