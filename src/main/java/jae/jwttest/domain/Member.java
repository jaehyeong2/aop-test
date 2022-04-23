package jae.jwttest.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public Member(String name, String password, String email, Gender gender) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }
}
