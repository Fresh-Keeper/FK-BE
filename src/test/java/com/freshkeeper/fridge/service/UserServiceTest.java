package com.freshkeeper.fridge.service;

import com.freshkeeper.fridge.dto.SignupRequest;
import com.freshkeeper.fridge.domain.User;
import com.freshkeeper.fridge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너를 띄워서 테스트 (의존성 주입 가능)
@Transactional   // 테스트가 끝나면 DB를 롤백해줌
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    void 회원가입() throws Exception {
        // given
        SignupRequest dto = SignupRequest.builder()
                .userId("test@naver.com")
                .userPassword("1234")
                .userName("김철수")
                .build();

        // when
        Long savedId = userService.signup(dto);

        // then
        User findUser = userRepository.findById(savedId).get();
        assertThat(findUser.getUserId()).isEqualTo(dto.getUserId());
    }

    @Test
    void 중복_회원_예외() throws Exception {
        // given
        SignupRequest dto1 = SignupRequest.builder().userId("user1").build();
        SignupRequest dto2 = SignupRequest.builder().userId("user1").build();

        // when
        userService.signup(dto1);

        // then
        assertThrows(IllegalStateException.class, () -> {
            userService.signup(dto2); // 여기서 예외가 터져야 테스트 성공!
        });
    }
}
