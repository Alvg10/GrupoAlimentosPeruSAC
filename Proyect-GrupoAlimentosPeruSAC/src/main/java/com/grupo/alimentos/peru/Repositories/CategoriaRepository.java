package com.grupo.alimentos.peru.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo.alimentos.peru.Entities.Categoria;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombreCategoriaIgnoreCase(String nombreCategoria);
    boolean existsByNombreCategoriaIgnoreCase(String nombreCategoria);
}
