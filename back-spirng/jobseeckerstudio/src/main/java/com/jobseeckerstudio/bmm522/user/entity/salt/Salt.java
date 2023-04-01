//package com.jobseeckerstudio.bmm522.user.entity.salt;
//
//import com.jobseeckerstudio.bmm522.user.entity.user.User;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Salt {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id", referencedColumnName = "id")
//    private User user;
//
//    @Column(nullable = false)
//    private String salt;
//
//}
