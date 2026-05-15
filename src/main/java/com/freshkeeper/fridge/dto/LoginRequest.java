package com.freshkeeper.fridge.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    private String userId;
    private String userPassword;
}
