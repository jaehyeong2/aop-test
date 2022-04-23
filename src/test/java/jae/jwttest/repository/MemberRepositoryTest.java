package jae.jwttest.repository;

import jae.jwttest.domain.Gender;
import jae.jwttest.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("이름으로 찾기")
    void findByNameTest(){
        Member memberA = new Member("memberA","","ww@123", Gender.MALE);
        memberRepository.save(memberA);

        Member findMember = memberRepository.findByName("memberA");
        Assertions.assertThat(findMember.getName()).isEqualTo("memberA");
    }

}