package com.grupo.alimentos.peru.dto;
import com.grupo.alimentos.peru.util.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiendaRequestDTO {

    @NotBlank(message = Messages.DESCRIPTION_EMPTY)
    @Size(max = 250, message = Messages.DESCRIPTION_TOO_LONG_250)
    private String nombreTienda;

    @NotBlank(message = Messages.DESCRIPTION_EMPTY)
    @Size(max = 250, message = Messages.DESCRIPTION_TOO_LONG_250)
    private String direccionTienda;

    @NotBlank(message = Messages.DESCRIPTION_EMPTY)
    @Size(max = 250, message = Messages.DESCRIPTION_TOO_LONG_250)
    private String distritoTienda;

    
}
