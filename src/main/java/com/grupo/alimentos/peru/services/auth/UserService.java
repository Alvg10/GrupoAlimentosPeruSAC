package com.grupo.alimentos.peru.services.auth;

import java.util.List;

import com.grupo.alimentos.peru.dto.auth.UserRequestDTO;
import com.grupo.alimentos.peru.dto.auth.UserResponseDTO;
import com.grupo.alimentos.peru.entity.auth.ERole;

public interface UserService {

    UserResponseDTO register(UserRequestDTO request);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long id, UserRequestDTO request);
    
    void deleteUser(Long id);
    
    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> filterUsers(String username, Long tiendaId, ERole role);

}
