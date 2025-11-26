package com.grupo.alimentos.peru.DTOs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class ProductoRequestDTO {
    
    @NotBlank(message = "Producto vacio")
    @Size(max = 100, message = " No debe exceder los 100 caracteres")
    private String nombreProducto;
    
    @Positive(message = "the price must be greater than zero")
    private double precioProducto;

    private Long idCategoria;
   
    private Long idTienda;

}
