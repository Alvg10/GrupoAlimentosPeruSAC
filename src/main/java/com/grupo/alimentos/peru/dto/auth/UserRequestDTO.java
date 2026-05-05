package com.grupo.alimentos.peru.dto.auth;
import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    
    @NotBlank
    private String username; 

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Set<String> roles;

    private Long idTienda;
}


// Not Null : String
// NotEmpty: Colecciones
// NotNull: Objetos