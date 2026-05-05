package com.grupo.alimentos.peru.dto;
import java.math.BigDecimal;
import com.grupo.alimentos.peru.util.Messages;
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
    
    @NotBlank(message = Messages.DESCRIPTION_EMPTY)
    @Size(max = 150, message = Messages.DESCRIPTION_TOO_LONG_150)
    private String nombreProducto;
    
    @Positive(message = Messages.PRICE_GREATER_CERO)
    private BigDecimal precioProducto;

    private Long idCategoria;
   
    private Long idTienda;

}
