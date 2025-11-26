package com.grupo.alimentos.peru.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grupo.alimentos.peru.DTOs.ProductoRequestDTO;
import com.grupo.alimentos.peru.DTOs.ProductoResponseDTO;
import com.grupo.alimentos.peru.Entities.Producto;

@Component
public class ProductoMapper {
    

    private final ModelMapper modelmapper;

    public ProductoMapper(ModelMapper modelmapper){
        this.modelmapper = modelmapper;
    }
    public ProductoResponseDTO toResponseDTO(Producto product){
        return modelmapper.map(product, ProductoResponseDTO.class);
    }
    public Producto toRequestDTO ( ProductoRequestDTO productoRequest){
        return modelmapper.map(productoRequest, Producto.class);
    }

}
