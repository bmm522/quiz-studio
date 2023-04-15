package com.jobseeckerstudio.bmm522.user.entity.user;

import com.jobseeckerstudio.bmm522.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userKey;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Builder
    public User(long id, String userKey, String password, String email, Status status) {
        this.id = id;
        this.userKey = userKey;
        this.password = password;
        this.email = email;
        this.status = status;
    }

    public void setEmailWithEncryption(String emailWithEncryption) {
        this.email = emailWithEncryption;
    }
}
