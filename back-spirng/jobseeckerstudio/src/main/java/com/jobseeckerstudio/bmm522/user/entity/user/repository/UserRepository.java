package com.jobseeckerstudio.bmm522.user.entity.user.repository;

import com.jobseeckerstudio.bmm522.user.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
