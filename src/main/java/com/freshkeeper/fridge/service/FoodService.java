package com.freshkeeper.fridge.service;

import com.freshkeeper.fridge.dto.FoodResponse;
import com.freshkeeper.fridge.repository.FoodRepository;
import lombok.RequiredArgsConstructor; // Lombok: final 필드를 파라미터로 받는 생성자 자동 생성 (의존성 주입용)
import org.springframework.stereotype.Service; // 이 클래스가 비즈니스 로직을 담당하는 서비스 컴포넌트임을 선언

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository; // final: 한번 주입되면 변경 불가

    // DB에서 전체 음식 목록 조회 후 FoodResponse DTO 리스트로 변환해서 반환
    public List<FoodResponse> getAllFoods() {
        return foodRepository.findAll()       // DB에서 모든 Food 조회
                .stream()                     // 리스트를 스트림으로 변환 (반복 처리용)
                .map(FoodResponse::new)        // 각 Food 객체를 FoodResponse로 변환
                .collect(Collectors.toList()); // 변환된 결과를 다시 리스트로 묶기
    }
}
