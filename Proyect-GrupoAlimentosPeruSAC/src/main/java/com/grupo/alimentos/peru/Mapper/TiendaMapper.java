package com.grupo.alimentos.peru.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grupo.alimentos.peru.DTOs.TiendaRequestDTO;
import com.grupo.alimentos.peru.DTOs.TiendaResponseDTO;
import com.grupo.alimentos.peru.Entities.Tienda;


@Component
public class TiendaMapper {
    
    private final ModelMapper modelMapper;
    public TiendaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public TiendaResponseDTO toResponseDTO(Tienda entity){
        return modelMapper.map(entity, TiendaResponseDTO.class);
    }
    public Tienda toRequestEntity(TiendaRequestDTO dto){
        return modelMapper.map(dto, Tienda.class);
    }
}


