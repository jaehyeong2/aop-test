package jae.jwttest.repository;

import jae.jwttest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByName(String name);
    Member findByEmail(String email);
}
