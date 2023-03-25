package com.jobseeckerstudio.bmm522.user.entity.repository;

import com.jobseeckerstudio.bmm522.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
