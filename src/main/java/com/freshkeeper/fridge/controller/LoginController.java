package com.freshkeeper.fridge.controller;


import com.freshkeeper.fridge.domain.User;
import com.freshkeeper.fridge.service.UserService;
import com.freshkeeper.fridge.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final UserService userService;

    //로그인 실행
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){
        try{
            // 1. 서비스 로직 호출 (아이디/비번 검증)
            User user = userService
                    .login(loginRequest.getUserId(), loginRequest.getUserPassword());

            // 2. 로그인 성공 처리: 세션 생성
            // getSession()은 기존 세션이 있으면 반환, 없으면 신규 생성
            HttpSession session = request.getSession();
            session.setAttribute("userId", user);

            return ResponseEntity.ok("로그인 성공, 세션이 생성되었습니다.");

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }

}
