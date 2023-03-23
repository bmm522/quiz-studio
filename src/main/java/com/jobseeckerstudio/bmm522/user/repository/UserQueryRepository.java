package com.jobseeckerstudio.bmm522.user.repository;

import com.jobseeckerstudio.bmm522.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQueryRepository extends JpaRepository<User, Long> {
    User findByUserKey(String username);
}
