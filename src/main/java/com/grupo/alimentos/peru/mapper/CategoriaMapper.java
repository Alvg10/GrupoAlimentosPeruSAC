package com.grupo.alimentos.peru.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grupo.alimentos.peru.dto.CategoriaRequestDTO;
import com.grupo.alimentos.peru.dto.CategoriaResponseDTO;
import com.grupo.alimentos.peru.entity.Categoria;

@Component
public class CategoriaMapper {
    
    private final ModelMapper modelMapper;
    public CategoriaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper; //Inyection por constructor
    }

    public CategoriaResponseDTO toDTO(Categoria categoria){
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    public Categoria toEntity(CategoriaRequestDTO categoriaRequest){
        return modelMapper.map(categoriaRequest, Categoria.class);
    }


}
