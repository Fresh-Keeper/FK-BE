package com.freshkeeper.fridge.controller;


import com.freshkeeper.fridge.domain.User;
import com.freshkeeper.fridge.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    //로그인 페이지 이동
    @GetMapping("/login")
    public String loginForm(){
        return "login/loginForm";
    }

    //로그인 실행
    @PostMapping("/login")
    public String login(@RequestParam String loginId,
                        @RequestParam String password,
                        HttpServletRequest request){
        try{
            // 1. 서비스 로직 호출 (아이디/비번 검증)
            User userId = userService.login(loginId, password); //loginUser -> userId로 변수명 변경

            // 2. 로그인 성공 처리: 세션 생성
            // getSession()은 기존 세션이 있으면 반환, 없으면 신규 생성
            HttpSession session = request.getSession();
            session.setAttribute("userId",userId);

            return "redirect:/main";

        } catch (IllegalArgumentException e) {
            return "login/loginForm";
        }
    }

}
