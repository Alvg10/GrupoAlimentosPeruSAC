package com.grupo.alimentos.peru.Services.Impl;
import java.util.List;
import java.util.stream.Collectors;
import com.grupo.alimentos.peru.DTOs.TiendaRequestDTO;
import com.grupo.alimentos.peru.DTOs.TiendaResponseDTO;
import com.grupo.alimentos.peru.Entities.Tienda;
import com.grupo.alimentos.peru.Exceptions.AlreadyExistsException;
import com.grupo.alimentos.peru.Exceptions.ResourceNotFoundException;
import com.grupo.alimentos.peru.Mapper.TiendaMapper;
import com.grupo.alimentos.peru.Repositories.TiendaRepository;
import com.grupo.alimentos.peru.Services.TiendaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TiendaServiceImpl implements TiendaService {
    

    private final TiendaRepository tiendaRepository; 
    private final TiendaMapper tiendaMapper;
    @Override
    public TiendaResponseDTO crearTienda(TiendaRequestDTO tiendaRequestDTO) {
        if(tiendaRepository.existsByNombreTiendaIgnoreCase(tiendaRequestDTO.getNombreTienda())){
             throw new AlreadyExistsException ("Parece que la tienda ya existe ;)");}   
        return tiendaMapper.toDTO(tiendaRepository.save(tiendaMapper.toEntity(tiendaRequestDTO)));  
    }
    @Override
    public List<TiendaResponseDTO> listarTienda() {
        List<Tienda> tiendaList = tiendaRepository.findAll();
        return tiendaList.stream()
            .map(tiendaMapper::toDTO)
            .collect(Collectors.toList());
    }
    @Override
    public TiendaResponseDTO obtenerTiendaPorID(Long idTienda) {
        Tienda tiendaID = tiendaRepository.findById(idTienda)   
            .orElseThrow(() -> new ResourceNotFoundException("Tienda no existe"));
        return tiendaMapper.toDTO(tiendaID);      
    }
    @Override
    public TiendaResponseDTO obtenerTiendaPorNombre(String nombreTienda) {
     Tienda tiendaID = tiendaRepository.findByNombreTiendaIgnoreCase(nombreTienda)   
            .orElseThrow(() -> new ResourceNotFoundException("Tienda no existe"));
        return tiendaMapper.toDTO(tiendaID);    
    }
    @Override
    public TiendaResponseDTO actualizarTienda(Long idActualizarTienda, TiendaRequestDTO tiendaDTO) {
        Tienda tiendaID = tiendaRepository.findById(idActualizarTienda)   
            .orElseThrow(() -> new ResourceNotFoundException("Tienda no existe para actualizar"));
        tiendaID.setDireccionTienda(tiendaDTO.getDireccionTienda());
        tiendaID.setNombreTienda(tiendaDTO.getNombreTienda());
        tiendaID.setDistritoTienda(tiendaDTO.getDistritoTienda());
        tiendaRepository.save(tiendaID);
        return tiendaMapper.toDTO(tiendaID);    
    }
    @Override
    public void eliminarTienda(Long idTienda) {
        Tienda tiendaDelete  = tiendaRepository.findById(idTienda)
        .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada"));
        tiendaRepository.delete(tiendaDelete);
       }
   
}
