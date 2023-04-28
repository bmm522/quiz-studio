package com.jobseeckerstudio.user.domain.salt.repository;

import com.jobseeckerstudio.user.domain.salt.Salt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaltRepository extends JpaRepository<Salt, Long> {
    Optional<Salt> findBySalt(String refreshToken);
}
