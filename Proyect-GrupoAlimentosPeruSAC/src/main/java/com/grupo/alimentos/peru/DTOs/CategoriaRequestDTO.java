package com.grupo.alimentos.peru.DTOs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO {

    @NotBlank(message = "Categoria vacia")
    @Size(max = 100, message = " No debe exceder los 100 caracteres")
    private String nombreCategoria;
    @NotBlank(message = "Descripcion vacia")
    @Size(max = 255, message = " No debe exceder los 250 caracteres")
    private String descripcionCategoria;

  
}
