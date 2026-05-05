package com.grupo.alimentos.peru.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.grupo.alimentos.peru.entity.Tienda;


// @Repository Redundante
public interface TiendaRepository extends JpaRepository<Tienda, Long>{
    Optional<Tienda>findByNombreTiendaIgnoreCase(String nombreTienda);
    boolean existsByNombreTiendaIgnoreCase(String nombreTienda);
}


