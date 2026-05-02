package com.grupo.alimentos.peru.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
