package com.freshkeeper.fridge.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class LoginRequest {
    private String userId;
    private String userPassword;
}
