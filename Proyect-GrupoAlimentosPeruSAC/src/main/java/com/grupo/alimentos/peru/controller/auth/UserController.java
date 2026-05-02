package com.grupo.alimentos.peru.controller.auth;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.grupo.alimentos.peru.dto.auth.UserRequestDTO;
import com.grupo.alimentos.peru.dto.auth.UserResponseDTO;
import com.grupo.alimentos.peru.entity.auth.ERole;
import com.grupo.alimentos.peru.services.auth.UserService;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        //return ResponseEntity.ok(userService.getUserById(id));   // alternativa corta
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);  //Forma larga
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UserRequestDTO request) {

        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserResponseDTO>> filtrarUsuarios(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Long tiendaId,
            @RequestParam(required = false) ERole role) {

        return ResponseEntity.ok(
                userService.filterUsers(username, tiendaId, role)
        );
    }
}

