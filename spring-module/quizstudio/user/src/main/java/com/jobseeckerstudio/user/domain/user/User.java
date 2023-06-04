package com.jobseeckerstudio.user.domain.user;

import com.jobseeckerstudio.user.domain.BaseTimeEntity;
import com.jobseeckerstudio.user.domain.Status;
import com.jobseeckerstudio.user.encrypt.Encryptor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "user_key", nullable = false)
	private String userKey;

	@Column(nullable = true)
	private String password;

	@Column(nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(nullable = false)
	private String salt;

	@Builder
	public User(long id, String userKey, String password, String email, Status status) {
		this.id = id;
		this.userKey = userKey;
		this.password = password;
		this.email = email;
		this.status = status;
	}

	public void setSalt(String refreshToken) {
		this.salt = refreshToken;
	}

	public void setEmailWithEncryption() {
		this.email = Encryptor.encrypt(this.email);
	}
}
