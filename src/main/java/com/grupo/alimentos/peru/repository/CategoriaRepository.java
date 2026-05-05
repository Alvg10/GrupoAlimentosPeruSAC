package com.grupo.alimentos.peru.repository;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.grupo.alimentos.peru.entity.Categoria;

import java.util.Optional;

// @Repository Redundante
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombreCategoriaIgnoreCase(String nombreCategoria);
    boolean existsByNombreCategoriaIgnoreCase(String nombreCategoria);
}
