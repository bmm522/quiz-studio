package com.jobseeckerstudio.user.repository.user;

import com.jobseeckerstudio.user.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserKey(String username);

	Optional<User> findBySalt(String refreshToken);

	@Transactional
	void deleteByUserKey(String userKey);
}
