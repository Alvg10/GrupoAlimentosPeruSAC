package com.grupo.alimentos.peru.dto;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class ProductoResponseDTO {

    private Long idProducto;
    
    private String nombreProducto;
    
    private BigDecimal precioProducto;

    private int stock;

    private CategoriaResponseDTO categoria;
   
    private TiendaResponseDTO tienda;

}
