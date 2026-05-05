package com.grupo.alimentos.peru.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiendaResponseDTO {

    // camelCase
    private Long idTienda;
    private String nombreTienda;
    private String direccionTienda;
    private String distritoTienda;

    
}
