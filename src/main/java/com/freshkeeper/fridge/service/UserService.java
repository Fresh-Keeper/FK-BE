package com.freshkeeper.fridge.service;

import com.freshkeeper.fridge.domain.User;
import com.freshkeeper.fridge.dto.SignupRequest;
import com.freshkeeper.fridge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입 기능
    public Long signup(SignupRequest dto){
        // 1. 중복 회원 검증(이메일 체크)
        validateDuplicateUser(dto.getUserId());

        // 2. 객체 변환(dto->domain)
        User user = User.builder()
                .userId(dto.getUserId())
                .userPassword(dto.getUserPassword())
                .userName(dto.getUserName())
                .build();

        // 3. 객체 저장
        userRepository.save(user);
        return user.getId();
    }

    //중복 회원 검증 로직
    private void validateDuplicateUser(String userId){
        userRepository.findByUserId(userId)
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    //로그인 기능
    public User login(String userId, String userPassword) {
        // 1. loginId가 존재하면 저장, 아니면 오류메시지 출력후 종료
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        // 2. 비밀번호가 일치하지 않으면 오류메시지 출력후 종료
        if(!user.getUserPassword().equals(userPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
