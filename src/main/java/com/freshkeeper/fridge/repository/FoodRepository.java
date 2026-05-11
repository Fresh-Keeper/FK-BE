package com.freshkeeper.fridge.repository;

import com.freshkeeper.fridge.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티 타입, PK 타입>을 상속받으면 기본 CRUD 메서드 자동 제공
// findAll(), findById(), save(), delete() 등을 직접 구현 없이 바로 사용 가능
public interface FoodRepository extends JpaRepository<Food, Long> {
}
