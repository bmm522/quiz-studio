package com.jobseecker.jobseeckerstudio.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserKey(String username);
}
