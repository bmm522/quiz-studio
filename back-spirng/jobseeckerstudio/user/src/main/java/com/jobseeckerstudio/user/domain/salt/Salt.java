package com.jobseeckerstudio.user.domain.salt;

import com.jobseeckerstudio.user.domain.BaseTimeEntity;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
public class Salt {

    @LastModifiedDate
    private LocalDateTime updateAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userKey;

    @Column(nullable = false)
    private String salt;


}
