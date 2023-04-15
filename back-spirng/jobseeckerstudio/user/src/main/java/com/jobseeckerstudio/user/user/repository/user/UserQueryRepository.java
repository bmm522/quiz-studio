package com.jobseeckerstudio.user.user.repository.user;

import com.jobseeckerstudio.user.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserKey(String username);
}
