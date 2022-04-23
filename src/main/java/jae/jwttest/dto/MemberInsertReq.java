package jae.jwttest.dto;

import jae.jwttest.domain.Gender;
import jae.jwttest.domain.Member;
import lombok.Getter;

@Getter
public class MemberInsertReq {

    private String name;
    private String password;
    private String email;
    private Gender gender;

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .gender(gender)
                .build();
    }
}
