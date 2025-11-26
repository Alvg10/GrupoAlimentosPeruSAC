package com.grupo.alimentos.peru.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class ProductoResponseDTO {

    private Long idProducto;
    
    private String nombreProducto;
    
    private double precioProducto;

    private int stock;

    private CategoriaResponseDTO categoria;
   
    private TiendaResponseDTO tienda;

}
