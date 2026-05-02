package com.grupo.alimentos.peru.repository.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo.alimentos.peru.entity.auth.ERole;
import com.grupo.alimentos.peru.entity.auth.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<UserEntity> findByUsernameContainingIgnoreCase(String username);

    List<UserEntity> findByTienda_IdTienda(Long tiendaId);

    List<UserEntity> findByRoles_Name(ERole role);

    @Query("""
            SELECT DISTINCT u FROM UserEntity u
            LEFT JOIN u.roles r
            LEFT JOIN u.tienda t
            WHERE (:username IS NULL OR LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%')))
            AND (:tiendaId IS NULL OR t.idTienda = :tiendaId)
            AND (:role IS NULL OR r.name = :role)
            """)
    List<UserEntity> filterUsers(
            @Param("username") String username,
            @Param("tiendaId") Long tiendaId,
            @Param("role") ERole role);

}
