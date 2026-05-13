package com.freshkeeper.fridge.dto;

import com.freshkeeper.fridge.entity.Food;
import com.freshkeeper.fridge.entity.FoodStatus;
import lombok.Getter; // Lombok: 모든 필드의 getter 메서드 자동 생성

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class FoodResponse { // DTO: 클라이언트에게 반환할 데이터만 담는 클래스

    private Long id;                      // 음식 고유 ID
    private String name;                  // 음식 이름
    private LocalDate expirationDate;     // 유통기한
    private FoodStatus status;            // 상태 (FRESH, EXPIRED, CONSUMED)
    private String imageUrl;              // 음식 사진 URL
    private String memo;                  // 메모
    private LocalDateTime createdAt;      // 등록 일시
    private Long registeredByUserId;      // 등록한 유저 ID
    private Long refrigeratorId;          // 소속 냉장고 ID

    // Food 엔티티를 받아서 응답 DTO로 변환하는 생성자
    public FoodResponse(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.expirationDate = food.getExpirationDate();
        this.status = food.getStatus();
        this.imageUrl = food.getImageUrl();
        this.memo = food.getMemo();
        this.createdAt = food.getCreatedAt();
        this.registeredByUserId = food.getRegisteredByUserId();
        this.refrigeratorId = food.getRefrigeratorId();
    }
}
