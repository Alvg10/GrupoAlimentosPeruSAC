package com.grupo.alimentos.peru.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grupo.alimentos.peru.dto.TiendaRequestDTO;
import com.grupo.alimentos.peru.dto.TiendaResponseDTO;
import com.grupo.alimentos.peru.entity.Tienda;


@Component
public class TiendaMapper {
    private final ModelMapper modelMapper;
    public TiendaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }




    public TiendaResponseDTO toDTO(Tienda entity){
        return modelMapper.map(entity, TiendaResponseDTO.class);
    }


    public Tienda toEntity(TiendaRequestDTO dto){
        return modelMapper.map(dto, Tienda.class);


    }
}


