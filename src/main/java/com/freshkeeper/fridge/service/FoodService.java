package com.freshkeeper.fridge.service;

import com.freshkeeper.fridge.dto.FoodRequest;
import com.freshkeeper.fridge.dto.FoodResponse;
import com.freshkeeper.fridge.entity.Food;
import com.freshkeeper.fridge.repository.FoodRepository;
import org.springframework.transaction.annotation.Transactional; // DB 작업을 하나의 트랜잭션으로 묶음
import lombok.RequiredArgsConstructor; // Lombok: final 필드를 파라미터로 받는 생성자 자동 생성 (의존성 주입용)
import org.springframework.stereotype.Service; // 이 클래스가 비즈니스 로직을 담당하는 서비스 컴포넌트임을 선언

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository; // final: 한번 주입되면 변경 불가

    // 새로운 음식 등록 후 저장된 데이터를 FoodResponse로 반환
    @Transactional // DB에 저장하는 작업이므로 트랜잭션 처리
    public FoodResponse createFood(FoodRequest request) {
        Food food = Food.builder()                              // 빌더 패턴으로 Food 객체 생성
                .name(request.getName())
                .expirationDate(request.getExpirationDate())
                .status(request.getStatus())
                .imageUrl(request.getImageUrl())
                .memo(request.getMemo())
                .registeredByUserId(request.getRegisteredByUserId())
                .refrigeratorId(request.getRefrigeratorId())
                .build();
        return new FoodResponse(foodRepository.save(food));    // DB에 저장 후 응답 반환
    }

    // ID로 음식 조회 후 수정, 없으면 예외 발생
    @Transactional
    public FoodResponse updateFood(Long id, FoodRequest request) {
        Food food = foodRepository.findById(id) // ID로 DB 조회
                .orElseThrow(() -> new IllegalArgumentException("음식을 찾을 수 없습니다. id: " + id)); // 없으면 에러
        food.update(request.getName(), request.getExpirationDate(), request.getStatus(),
                request.getImageUrl(), request.getMemo()); // 수정할 필드 업데이트
        return new FoodResponse(food); // 수정된 데이터 반환 (@Transactional이 자동으로 DB에 반영)
    }

    // ID로 음식 조회 후 삭제, 없으면 예외 발생
    @Transactional
    public void deleteFood(Long id) {
        Food food = foodRepository.findById(id) // ID로 DB 조회
                .orElseThrow(() -> new IllegalArgumentException("음식을 찾을 수 없습니다. id: " + id)); // 없으면 에러
        foodRepository.delete(food); // DB에서 삭제
    }

    // 특정 음식 상태를 CONSUMED로 변경 (소비 완료 처리)
    @Transactional
    public FoodResponse consumeFood(Long id) {
        Food food = foodRepository.findById(id) // ID로 DB 조회
                .orElseThrow(() -> new IllegalArgumentException("음식을 찾을 수 없습니다. id: " + id)); // 없으면 에러
        food.consume(); // 상태를 CONSUMED로 변경
        return new FoodResponse(food); // 변경된 데이터 반환
    }

    // DB에서 전체 음식 목록 조회 후 FoodResponse DTO 리스트로 변환해서 반환
    public List<FoodResponse> getAllFoods() {
        return foodRepository.findAll()       // DB에서 모든 Food 조회
                .stream()                     // 리스트를 스트림으로 변환 (반복 처리용)
                .map(FoodResponse::new)        // 각 Food 객체를 FoodResponse로 변환
                .collect(Collectors.toList()); // 변환된 결과를 다시 리스트로 묶기
    }
}
