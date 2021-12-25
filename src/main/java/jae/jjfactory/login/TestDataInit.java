package jae.jjfactory.login;

import jae.jjfactory.login.domain.item.Item;
import jae.jjfactory.login.domain.item.ItemRepository;
import jae.jjfactory.login.domain.member.Member;
import jae.jjfactory.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("1234!");
        member.setName("테스터");

        memberRepository.save(member);
    }
}
