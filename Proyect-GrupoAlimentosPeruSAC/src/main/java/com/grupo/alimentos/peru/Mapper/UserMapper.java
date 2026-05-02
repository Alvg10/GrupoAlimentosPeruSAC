package com.grupo.alimentos.peru.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grupo.alimentos.peru.dto.auth.UserRequestDTO;
import com.grupo.alimentos.peru.dto.auth.UserResponseDTO;
import com.grupo.alimentos.peru.entity.auth.UserEntity;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO toDTO(UserEntity user) {
        UserResponseDTO dto = modelMapper.map(user, UserResponseDTO.class);
        // convertir roles (Entity → String)
        Set<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
        dto.setRoles(roles);
        if (user.getTienda() != null) {
            dto.setIdTienda(user.getTienda().getIdTienda());
        }
        return dto;
    }

    public UserEntity toEntity(UserRequestDTO request) {
        UserEntity user = modelMapper.map(request, UserEntity.class);
        user.setId(null);
        return user;
    }

}
