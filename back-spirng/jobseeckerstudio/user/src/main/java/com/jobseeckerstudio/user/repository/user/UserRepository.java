package com.jobseeckerstudio.user.repository.user;

import com.jobseeckerstudio.user.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserKey(String username);
}
