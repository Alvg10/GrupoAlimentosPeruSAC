package com.grupo.alimentos.peru.config.security;
import com.grupo.alimentos.peru.entity.auth.UserEntity;
import com.grupo.alimentos.peru.repository.auth.UserRepository;
import com.grupo.alimentos.peru.util.Messages;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserEntity user = userRepository.findByUsername(username)

                .orElseThrow(() -> new UsernameNotFoundException(Messages.USER_NOT_FOUND));

        Long tiendaId = (user.getTienda() != null) ? user.getTienda().getIdTienda() : null; 


        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName().name()))
                        .toList(),
               tiendaId
        );
    }
}
