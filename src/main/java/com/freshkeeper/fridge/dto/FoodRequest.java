package com.freshkeeper.fridge.dto;

import com.freshkeeper.fridge.entity.FoodStatus;
import lombok.Getter; // Lombok: 모든 필드의 getter 메서드 자동 생성
import lombok.NoArgsConstructor; // Lombok: 파라미터 없는 기본 생성자 자동 생성 (JSON 역직렬화 필수)

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class FoodRequest { // 음식 등록/수정 요청 시 클라이언트에서 받는 데이터

    private String name;           // 음식 이름
    private LocalDate expirationDate; // 유통기한
    private FoodStatus status;     // 상태 (FRESH, EXPIRED, CONSUMED) - 미입력 시 FRESH로 기본값 처리
    private String imageUrl;       // 음식 사진 URL (선택)
    private String memo;           // 메모 (선택)
    private Long registeredByUserId; // 등록한 유저 ID
    private Long refrigeratorId;   // 소속 냉장고 ID
}
