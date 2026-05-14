package com.freshkeeper.fridge.controller;

import com.freshkeeper.fridge.dto.FoodRequest;
import com.freshkeeper.fridge.dto.FoodResponse;
import com.freshkeeper.fridge.service.FoodService;
import lombok.RequiredArgsConstructor; // Lombok: final 필드를 파라미터로 받는 생성자 자동 생성 (의존성 주입용)
import org.springframework.http.ResponseEntity; // HTTP 상태코드와 응답 데이터를 함께 반환하는 클래스
import org.springframework.web.bind.annotation.*; // @GetMapping, @PostMapping, @RequestBody 등 포함

import java.util.List;

@RestController // @Controller + @ResponseBody: 반환값을 JSON으로 자동 변환
@RequestMapping("/foods") // 이 컨트롤러의 모든 요청은 /foods 로 시작
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService; // final: 한번 주입되면 변경 불가

    // GET /foods - 모든 음식 목록 조회
    @GetMapping // GET 방식의 /foods 요청이 들어오면 이 메서드 실행
    public ResponseEntity<List<FoodResponse>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods()); // 200 OK와 함께 데이터 반환
    }

    // POST /foods - 새로운 음식 등록
    @PostMapping // POST 방식의 /foods 요청이 들어오면 이 메서드 실행
    public ResponseEntity<FoodResponse> createFood(@RequestBody FoodRequest request) {
        // @RequestBody: HTTP 요청 body의 JSON을 FoodRequest 객체로 자동 변환
        return ResponseEntity.ok(foodService.createFood(request)); // 200 OK와 함께 저장된 데이터 반환
    }

    // PUT /foods/{id} - 특정 음식 정보 수정
    @PutMapping("/{id}") // PUT 방식의 /foods/{id} 요청이 들어오면 이 메서드 실행
    public ResponseEntity<FoodResponse> updateFood(@PathVariable Long id, @RequestBody FoodRequest request) {
        // @PathVariable: URL의 {id} 값을 파라미터로 받음
        return ResponseEntity.ok(foodService.updateFood(id, request)); // 200 OK와 함께 수정된 데이터 반환
    }

    // DELETE /foods/{id} - 특정 음식 삭제
    @DeleteMapping("/{id}") // DELETE 방식의 /foods/{id} 요청이 들어오면 이 메서드 실행
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id); // 음식 삭제
        return ResponseEntity.noContent().build(); // 204 No Content 반환 (삭제 성공 시 반환할 데이터 없음)
    }
}
