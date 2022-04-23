package jae.jwttest.controller;

import jae.jwttest.domain.Member;
import jae.jwttest.dto.CommonRes;
import jae.jwttest.dto.MemberInsertReq;
import jae.jwttest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{id}")
    public CommonRes<?> getMemberById(@PathVariable Long id){
        return new CommonRes<>(memberService.getMember(id));
    }

    @GetMapping("/name")
    public CommonRes<?> getMemberByName(@RequestParam String name){
        return new CommonRes<>(memberService.getMemberByName(name));
    }

    @GetMapping("/email")
    public CommonRes<?> getMemberByEmail(@RequestParam String email){
        return new CommonRes<>(memberService.getMemberByEmail(email));
    }

    @GetMapping("/dup/email")
    public CommonRes<?> emailCheck(@RequestParam String email){
        boolean result = memberService.emailCheck(email);
        return new CommonRes<>(result);
    }

    @GetMapping("")
    public CommonRes<?> getMemberList(){
        return new CommonRes<>(memberService.getMemberList());
    }

    @PostMapping()
    public CommonRes<?> insertMember(@RequestBody MemberInsertReq dto){
        Member member = dto.toEntity();
        memberService.insert(member);
        return new CommonRes<>("ok", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public CommonRes<?> insertMember(@PathVariable Long id){
        memberService.delete(id);
        return new CommonRes<>("ok",HttpStatus.OK);
    }
}
