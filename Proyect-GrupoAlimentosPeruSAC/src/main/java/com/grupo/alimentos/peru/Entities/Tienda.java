package com.grupo.alimentos.peru.Entities;

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
public class Tienda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tienda", nullable = false)
    private Long idTienda;

    @Column(name = "nombre_tienda", nullable = false, length = 100)
    private String nombreTienda;

    @Column(name = "direccion_tienda", nullable = false, length = 200)
    private String direccionTienda;

    @Column(name = "distrito_tienda", nullable = false, length = 100)
    private String distritoTienda;

    @OneToMany(mappedBy = "tienda")
    private List<Producto> productos;
}
