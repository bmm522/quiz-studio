package com.jobseeckerstudio.bmm522.user.repository.user;

import com.jobseeckerstudio.bmm522.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQueryRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserKey(String username);
}
