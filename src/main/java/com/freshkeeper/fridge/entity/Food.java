package com.freshkeeper.fridge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // 이 클래스가 DB 테이블과 연결된 엔티티임을 선언
@Getter // Lombok: 모든 필드의 getter 메서드 자동 생성 (예: getId(), getName() ...)
@NoArgsConstructor // Lombok: 파라미터 없는 기본 생성자 자동 생성 (JPA 필수)
@Table(name = "food") // 연결할 DB 테이블 이름 지정
public class Food {

    @Id // 이 필드가 기본키(PK)임을 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동으로 1, 2, 3... 증가
    private Long id; // 음식 고유 ID

    @Column(nullable = false) // DB 컬럼 설정: NULL 불가 (NOT NULL)
    private String name; // 음식 이름

    private LocalDate expirationDate; // 유통기한

    @Enumerated(EnumType.STRING) // enum 값을 DB에 숫자(0,1,2) 대신 문자열("FRESH" 등)로 저장
    @Column(nullable = false)
    private FoodStatus status; // 상태 (FRESH, EXPIRED, CONSUMED)

    private String imageUrl; // 음식 사진 URL

    private String memo; // 메모

    @Column(nullable = false, updatable = false) // NULL 불가, 한번 저장 후 수정 불가
    private LocalDateTime createdAt; // 등록 일시

    private Long registeredByUserId; // 등록한 유저 ID

    private Long refrigeratorId; // 소속 냉장고 ID
}
