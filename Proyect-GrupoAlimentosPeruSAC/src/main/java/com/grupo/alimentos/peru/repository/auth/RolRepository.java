package com.grupo.alimentos.peru.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo.alimentos.peru.entity.auth.ERole;
import com.grupo.alimentos.peru.entity.auth.RoleEntity;

public interface RolRepository extends JpaRepository<RoleEntity, Long>{
    
        Optional<RoleEntity> findByName(ERole name);
    

}
