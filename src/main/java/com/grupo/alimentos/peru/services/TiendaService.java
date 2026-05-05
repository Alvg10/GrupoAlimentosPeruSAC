package com.grupo.alimentos.peru.services;
import java.util.List;

import com.grupo.alimentos.peru.dto.TiendaRequestDTO;
import com.grupo.alimentos.peru.dto.TiendaResponseDTO;

public interface TiendaService {
    
        TiendaResponseDTO crearTienda(TiendaRequestDTO tiendaRequestDTO);
        
        List<TiendaResponseDTO> listarTienda();

        TiendaResponseDTO obtenerTiendaPorID (Long idTienda);
        
        TiendaResponseDTO obtenerTiendaPorNombre (String nombreTienda);

        TiendaResponseDTO actualizarTienda (Long idActualizarTienda, TiendaRequestDTO tiendaDTO);
        
        void eliminarTienda (Long idTienda);
}
