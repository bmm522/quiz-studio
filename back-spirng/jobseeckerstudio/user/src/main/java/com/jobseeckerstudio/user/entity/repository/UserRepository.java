package com.jobseeckerstudio.user.entity.repository;

import com.jobseeckerstudio.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
