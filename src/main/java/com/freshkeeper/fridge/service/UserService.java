package com.freshkeeper.fridge.service;

import com.freshkeeper.fridge.domain.User;
import com.freshkeeper.fridge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


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
