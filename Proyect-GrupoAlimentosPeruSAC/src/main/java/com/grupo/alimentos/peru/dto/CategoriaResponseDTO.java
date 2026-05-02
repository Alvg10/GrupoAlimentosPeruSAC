package com.grupo.alimentos.peru.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponseDTO {

    private Long idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;

  
}
