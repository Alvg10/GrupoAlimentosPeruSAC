package com.grupo.alimentos.peru.entity;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    @Column(name="nombre_categoria", nullable = false, length = 100)
    private String nombreCategoria;

    @Column(name = "descripcion_categoria", nullable = false, length = 255)
    private String descripcionCategoria;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

}
