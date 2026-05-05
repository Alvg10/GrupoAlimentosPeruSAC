package com.grupo.alimentos.peru.services;
import java.util.List;

import com.grupo.alimentos.peru.dto.CategoriaRequestDTO;
import com.grupo.alimentos.peru.dto.CategoriaResponseDTO;
public interface CategoriaService {
    

        CategoriaResponseDTO crearCategoria(CategoriaRequestDTO categoriaRequestDto);
        
        List<CategoriaResponseDTO> listarCategoria();

        CategoriaResponseDTO obtenerCategoriaPorID (Long idcateg);
        
        CategoriaResponseDTO obtenerCategoriaPorNombre (String nombreCategoria);

        CategoriaResponseDTO actualizarCategoria (Long idCategoriaActualiza, CategoriaRequestDTO categoriaDTO);
        
        void eliminarCategoria (Long idCategoria);

/*

public interface CrudRepository<T, ID> extends Repository<T, ID> {

   Optional<T> findById(ID id);   <<<< Siempre devuelvo un Optional
}
*/


    /*
        CrearCategoria
        ListarCategoria
        ObtenerPorIDCategoria
        ActualizarCategoria
        EliminarCategoria
    
    */





}
