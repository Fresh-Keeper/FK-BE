package com.freshkeeper.fridge.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")  //DB의 예약어User를 우회하기 위해 이름 변경
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 설정
    private Long id;    //DB 식별자 (PK)

    private String loginId;
    private String password;
}