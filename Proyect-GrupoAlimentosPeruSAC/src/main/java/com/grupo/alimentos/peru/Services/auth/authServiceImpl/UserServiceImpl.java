package com.grupo.alimentos.peru.services.auth.authServiceImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo.alimentos.peru.dto.auth.UserRequestDTO;
import com.grupo.alimentos.peru.dto.auth.UserResponseDTO;
import com.grupo.alimentos.peru.entity.Tienda;
import com.grupo.alimentos.peru.entity.auth.ERole;
import com.grupo.alimentos.peru.entity.auth.RoleEntity;
import com.grupo.alimentos.peru.entity.auth.UserEntity;
import com.grupo.alimentos.peru.exception.AlreadyExistsException;
import com.grupo.alimentos.peru.exception.BadRequestException;
import com.grupo.alimentos.peru.exception.ResourceNotFoundException;
import com.grupo.alimentos.peru.mapper.UserMapper;
import com.grupo.alimentos.peru.repository.TiendaRepository;
import com.grupo.alimentos.peru.repository.auth.RolRepository;
import com.grupo.alimentos.peru.repository.auth.UserRepository;
import com.grupo.alimentos.peru.services.auth.UserService;
import com.grupo.alimentos.peru.util.Messages;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TiendaRepository tiendaRepository;

    @Override
    public UserResponseDTO register(UserRequestDTO request) {

        // 1. Validar duplicados
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException(Messages.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("Este email ya existe");
        }
        // 2. Convertir DTO → Entity
        UserEntity user = userMapper.toEntity(request);
        System.out.println("USER ID: " + user.getId());
        // 3. Encriptar password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 3.5 Asignar tienda
        if (request.getIdTienda() != null) {
            Tienda tienda = tiendaRepository.findById(request.getIdTienda())
                    .orElseThrow(() -> new ResourceNotFoundException(Messages.STORE_NOT_FOUND_ID));

            user.setTienda(tienda);
        } else {
            throw new BadRequestException(Messages.STORE_REQUIRED);
        }
        // 4. Asignar rol (por defecto si no viene)
        Set<RoleEntity> roles = new HashSet<>();
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            RoleEntity defaultRole = rolRepository.findByName(ERole.INVITED)
                    .orElseThrow(() -> new ResourceNotFoundException(Messages.DEFAULT_ROLE_NOT_FOUND));
            roles.add(defaultRole);
        } else {
            for (String roleStr : request.getRoles()) {

                ERole roleEnum = ERole.valueOf(roleStr.toUpperCase(Locale.ROOT)); // valueOf es Case Sensitive

                RoleEntity role = rolRepository.findByName(roleEnum)
                        .orElseThrow(() -> new ResourceNotFoundException(Messages.ROLE_NOT_FOUND));
                roles.add(role);
            }
        }
        user.setRoles(roles);
        // 5. Guardar
        UserEntity savedUser = userRepository.save(user);
        // 6. Convertir a DTO
        return userMapper.toDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.USER_NOT_FOUND));
        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(Messages.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Messages.USER_NOT_FOUND));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (request.getIdTienda() != null) {
            Tienda tienda = tiendaRepository.findById(request.getIdTienda())
                    .orElseThrow(() -> new ResourceNotFoundException(Messages.STORE_NOT_FOUND_ID));
            user.setTienda(tienda);
        }
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> filterUsers(String username, Long idTienda, ERole role) {

        List<UserEntity> users = userRepository.filterUsers(username, idTienda, role);

        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }
}
