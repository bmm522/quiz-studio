package com.jobseeckerstudio.bmm522.user.entity.salt;

import com.jobseeckerstudio.bmm522.user.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Salt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String salt;

    @Builder
    public Salt(String salt){
        this.salt = salt;
    }

    public void addUser(User user) {
        this.user = user;
        if(user.getSalt() != this){
            user.addSalt(this);
        }

    }

}
