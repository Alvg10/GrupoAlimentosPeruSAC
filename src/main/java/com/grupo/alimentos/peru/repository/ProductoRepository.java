package com.grupo.alimentos.peru.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.grupo.alimentos.peru.entity.Producto;



// @Repository Redundante
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByTienda_IdTienda(Long idTienda);
    List<Producto> findByCategoria_IdCategoria(Long idCategoria);
	boolean existsByNombreProductoIgnoreCaseAndTienda_IdTienda(String nombreProducto, Long idTienda);
	List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);  // Search Bar
}


/*

public interface CrudRepository<T, ID> extends Repository<T, ID> {

   Optional<T> findById(ID id);   <<<< Siempre devuelvo un Optional
}
*/    