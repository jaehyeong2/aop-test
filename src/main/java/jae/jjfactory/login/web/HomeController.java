package jae.jjfactory.login.web;

import jae.jjfactory.login.domain.member.Member;
import jae.jjfactory.login.domain.member.MemberRepository;
import jae.jjfactory.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@Slf4j
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId",required = false) Long memberId, Model model){

        if(memberId == null){
            return "home";
        }

        //로그인
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }


//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request,Model model){

        Member member = (Member)sessionManager.getSession(request);

        //로그인
        if(member == null){
            return "home";
        }
        model.addAttribute("member",member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request,Model model){

        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //로그인
        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV4(
            @SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false)Member member, Model model){
        //로그인
        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
}
