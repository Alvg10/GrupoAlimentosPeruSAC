package com.grupo.alimentos.peru.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiendaResponseDTO {

    private Long IdTienda;
    private String nombreTienda;
    private String direccionTienda;
    private String distritoTienda;

    
}
