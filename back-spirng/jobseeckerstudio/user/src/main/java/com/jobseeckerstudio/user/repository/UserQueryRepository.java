package com.jobseeckerstudio.user.repository;

import com.jobseeckerstudio.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserKey(String username);
}
