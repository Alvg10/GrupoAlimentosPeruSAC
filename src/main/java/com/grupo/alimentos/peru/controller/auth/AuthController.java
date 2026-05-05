package com.grupo.alimentos.peru.controller.auth;
import java.net.URI;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.grupo.alimentos.peru.config.security.JwtService;
import com.grupo.alimentos.peru.dto.auth.LoginRequest;
import com.grupo.alimentos.peru.dto.auth.UserRequestDTO;
import com.grupo.alimentos.peru.dto.auth.UserResponseDTO;
import com.grupo.alimentos.peru.services.auth.UserService;
import com.grupo.alimentos.peru.util.Messages;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO user = userService.register(request);

        return ResponseEntity
                .created(URI.create("/api/users/" + user.getId()))
                .body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        try{
                authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
                String token = jwtService.generateToken(request.getUsername());
                return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            throw new BadCredentialsException(Messages.INVALID_CREDENTIALS);
        }
        
    }

}
