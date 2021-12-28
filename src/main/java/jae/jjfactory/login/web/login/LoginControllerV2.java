package jae.jjfactory.login.web.login;

import jae.jjfactory.login.domain.login.LoginService;
import jae.jjfactory.login.domain.member.Member;
import jae.jjfactory.login.web.SessionConst;
import jae.jjfactory.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginControllerV2 {

    private final LoginService loginService;
    private final SessionManager sessionManager;

//    @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if (bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("login failed","아이디 또는 비밀번호가 일치하지 않습니다");
            return "/login/loginForm";
        }

        // 로그인 성공

        //세션생성, 회원정보 보관
        sessionManager.createSession(loginMember,response);
        return "redirect:/";
    }


//    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("login failed","아이디 또는 비밀번호가 일치하지 않습니다");
            return "/login/loginForm";
        }

        // 로그인 성공
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return "/login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("login failed","아이디 또는 비밀번호가 일치하지 않습니다");
            return "/login/loginForm";
        }

        // 로그인 성공
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        return "redirect:" + redirectURL;
    }

//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
