package jae.jwttest.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static jae.jwttest.domain.QMember.member;

import jae.jwttest.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepositorySupport {
    private final JPAQueryFactory queryFactory;


    public long emailDupCheck(String email){
        Member member = queryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne();

        if(member == null){
            return 1;
        }else{
            return 0;
        }
    }
}
