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

import com.grupo.alimentos.peru.dto.CategoriaRequestDTO;
import com.grupo.alimentos.peru.dto.CategoriaResponseDTO;
import com.grupo.alimentos.peru.services.CategoriaService;

import jakarta.validation.Valid;
@RestController
@Validated
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    public CategoriaController (CategoriaService categoriaService){
        this.categoriaService =  categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crearProducto (@Valid @RequestBody CategoriaRequestDTO crearPro){
        CategoriaResponseDTO nuevo = categoriaService.crearCategoria(crearPro);
        return ResponseEntity.created(URI.create("/api/categorias/" + nuevo.getIdCategoria())).body(nuevo);

    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaPorID(id));
    }

    @GetMapping("/buscarPorNombre")      
    public ResponseEntity<CategoriaResponseDTO> obtenerPorName( @RequestParam String nombreCateg) {
        return ResponseEntity.ok(categoriaService.obtenerCategoriaPorNombre(nombreCateg));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizarCateg(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO cateDto) {

        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, cateDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria (@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }


}
