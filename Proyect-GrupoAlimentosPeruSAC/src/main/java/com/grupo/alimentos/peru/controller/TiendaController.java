package com.grupo.alimentos.peru.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo.alimentos.peru.dto.TiendaRequestDTO;
import com.grupo.alimentos.peru.dto.TiendaResponseDTO;
import com.grupo.alimentos.peru.services.TiendaService;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping("/api/tiendas")

public class TiendaController {

    private final TiendaService tiendaService;
    public TiendaController (TiendaService tiendaService){
        this.tiendaService = tiendaService;
    }

    @PostMapping
    public ResponseEntity<TiendaResponseDTO> crearTienda(@Valid @RequestBody TiendaRequestDTO nuevaTiendita) {
        TiendaResponseDTO nuevaTienda = tiendaService.crearTienda(nuevaTiendita);
        return ResponseEntity
            .created(URI.create("/api/tiendas/" + nuevaTienda.getIdTienda()))
            .body(nuevaTienda);
    }

    
    @GetMapping
    public ResponseEntity<List<TiendaResponseDTO>> listar() {
        return ResponseEntity.ok(tiendaService.listarTienda());
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<TiendaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tiendaService.obtenerTiendaPorID(id));
    }

    
    @GetMapping("/buscar")
    public ResponseEntity<TiendaResponseDTO> obtenerPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(tiendaService.obtenerTiendaPorNombre(nombre));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<TiendaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TiendaRequestDTO dto) {

        return ResponseEntity.ok(tiendaService.actualizarTienda(id, dto));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTienda (@PathVariable Long id) {
        tiendaService.eliminarTienda(id);
        return ResponseEntity.noContent().build();
    }
}
