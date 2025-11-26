package com.grupo.alimentos.peru.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grupo.alimentos.peru.DTOs.CategoriaRequestDTO;
import com.grupo.alimentos.peru.DTOs.CategoriaResponseDTO;
import com.grupo.alimentos.peru.Entities.Categoria;

@Component
public class CategoriaMapper {
    

    private final ModelMapper modelMapper;
    public CategoriaMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public CategoriaResponseDTO toResponseDTO(Categoria categoria){
        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    public Categoria toRequestDTO(CategoriaRequestDTO categoriaRequest){
        return modelMapper.map(categoriaRequest, Categoria.class);
    }






}
