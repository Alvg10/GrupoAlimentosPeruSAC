package com.grupo.alimentos.peru.services.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.grupo.alimentos.peru.dto.TiendaRequestDTO;
import com.grupo.alimentos.peru.dto.TiendaResponseDTO;
import com.grupo.alimentos.peru.entity.Tienda;
import com.grupo.alimentos.peru.exception.AlreadyExistsException;
import com.grupo.alimentos.peru.exception.ResourceNotFoundException;
import com.grupo.alimentos.peru.mapper.TiendaMapper;
import com.grupo.alimentos.peru.repository.TiendaRepository;
import com.grupo.alimentos.peru.services.TiendaService;
import com.grupo.alimentos.peru.util.Messages;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TiendaServiceImpl implements TiendaService {
    

    private final TiendaRepository tiendaRepository; 
    private final TiendaMapper tiendaMapper;
    @Override
    public TiendaResponseDTO crearTienda(TiendaRequestDTO tiendaRequestDTO) {
        if(tiendaRepository.existsByNombreTiendaIgnoreCase(tiendaRequestDTO.getNombreTienda())){
             throw new AlreadyExistsException (Messages.STORE_ALREADY_EXISTS);}   
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
            .orElseThrow(() -> new ResourceNotFoundException(Messages.STORE_NOT_FOUND_ID));
        return tiendaMapper.toDTO(tiendaID);      
    }
    @Override
    public TiendaResponseDTO obtenerTiendaPorNombre(String nombreTienda) {
     Tienda tiendaID = tiendaRepository.findByNombreTiendaIgnoreCase(nombreTienda)   
            .orElseThrow(() -> new ResourceNotFoundException(Messages.STORE_NOT_FOUND_ID));
        return tiendaMapper.toDTO(tiendaID);    
    }
    @Override
    public TiendaResponseDTO actualizarTienda(Long idActualizarTienda, TiendaRequestDTO tiendaDTO) {
        Tienda tiendaID = tiendaRepository.findById(idActualizarTienda)   
            .orElseThrow(() -> new ResourceNotFoundException(Messages.STORE_NOT_FOUND_UPDATE));
        tiendaID.setDireccionTienda(tiendaDTO.getDireccionTienda());
        tiendaID.setNombreTienda(tiendaDTO.getNombreTienda());
        tiendaID.setDistritoTienda(tiendaDTO.getDistritoTienda());
        tiendaRepository.save(tiendaID);
        return tiendaMapper.toDTO(tiendaID);    
    }
    @Override
    public void eliminarTienda(Long idTienda) {
        Tienda tiendaDelete  = tiendaRepository.findById(idTienda)
        .orElseThrow(() -> new ResourceNotFoundException(Messages.STORE_NOT_FOUND));
        tiendaRepository.delete(tiendaDelete);
       }
   
}
