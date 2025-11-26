package com.grupo.alimentos.peru.DTOs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiendaRequestDTO {

    @NotBlank(message = "Nombre de tienda vacia")
    @Size(max = 200, message = " No debe exceder los 200 caracteres")
    private String nombreTienda;

    @NotBlank(message = "Direccion vacia")
    @Size(max = 200, message = " No debe exceder los 200 caracteres")
    private String direccionTienda;

    @NotBlank(message = "distrito vacio")
    @Size(max = 200, message = " No debe exceder los 200 caracteres")
    private String distritoTienda;

    
}
