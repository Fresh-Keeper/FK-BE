package com.freshkeeper.fridge.controller;


import com.freshkeeper.fridge.domain.User;
import com.freshkeeper.fridge.dto.SignupRequest;
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
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {   //리팩토링: LoginController -> UserController
    private final UserService userService;

    //회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest){
        try{
            // 1. 서비스 로직 호출 (회원가입 로직 실행)
            userService.signup(signupRequest);

            // 2. 성공 응답 반환
            return ResponseEntity.ok("회원가입이 완료되었습니다.");

        } catch (IllegalStateException e){
            // 3. 중복 가입 등 예외 발생 시 에러 메시시 반환
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //로그인 API
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

    //로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        userService.logout(session);

        return ResponseEntity.ok("로그아웃 성공");
    }
}
