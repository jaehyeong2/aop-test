package jae.jwttest.service;

import jae.jwttest.domain.Member;
import jae.jwttest.repository.MemberRepository;
import jae.jwttest.repository.MemberRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberRepositorySupport memberRepositorySupport;

    @Transactional(readOnly = true)
    public Member getMember(Long id){
        Member member = memberRepository.findById(id).get();
        return member;
    }

    @Transactional(readOnly = true)
    public Member getMemberByName(String name){
        Member member = memberRepository.findByName(name);
        return member;
    }

    @Transactional(readOnly = true)
    public Member getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        return member;
    }

    @Transactional(readOnly = true)
    public List<Member> getMemberList(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean emailCheck(String email) {
        long result = memberRepositorySupport.emailDupCheck(email);
        if(result == 1){
            return true;
        }
        return false;
    }

    public void insert(Member member){
        memberRepository.save(member);
    }

    public void delete(Long id){
        memberRepository.deleteById(id);
    }


}
