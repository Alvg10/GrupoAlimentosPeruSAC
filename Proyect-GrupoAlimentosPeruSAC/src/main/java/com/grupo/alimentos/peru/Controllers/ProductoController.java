package com.grupo.alimentos.peru.Controllers;

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

import com.grupo.alimentos.peru.DTOs.ProductoRequestDTO;
import com.grupo.alimentos.peru.DTOs.ProductoResponseDTO;
import com.grupo.alimentos.peru.Services.ProductoService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/producto")
public class ProductoController {

    //Inyeccion por construccion sin anotacion
    private final ProductoService productoService;
    public ProductoController (ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto (@Valid @RequestBody ProductoRequestDTO productoNuevo){
        ProductoResponseDTO nuevo = productoService.crearProducto(productoNuevo);
            URI ubicacion = URI.create("api/productos/" + nuevo.getIdProducto());
        return ResponseEntity.created(ubicacion).body(nuevo);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorID(@PathVariable Long productoID){
        return ResponseEntity.ok(productoService.obtenerProductosPorID(productoID));
    }

    @GetMapping("/listarProductos")
    public ResponseEntity<List<ProductoResponseDTO>> listaTodo (){
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductoPorCategoria(@PathVariable Long CategoriaID){
        return ResponseEntity.ok(productoService.listarProductoPorCategoria(CategoriaID));
    }


    @GetMapping("/{idTienda}")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductoPorTienda(@PathVariable Long tiendaID){
        return ResponseEntity.ok(productoService.listarProductosPorTienda(tiendaID));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombre(@RequestParam String nombre){
        return ResponseEntity.ok(productoService.buscarProductoPorCoincidencia(nombre));
    }


    @PutMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO>actualizarProducto(
        @PathVariable Long IdProduct,
        @Valid @RequestBody ProductoRequestDTO actualizarProduct){
        return ResponseEntity.ok(productoService.actualizarProducto(IdProduct, actualizarProduct));
        }


    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto (@PathVariable Long productoID){
        productoService.eliminarProducto(productoID);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{idProducto}/entrada/{cantidad}")
    public ResponseEntity<ProductoResponseDTO> registrarEntrada(@PathVariable Long productoID, @PathVariable int cantidad){
        return ResponseEntity.ok(productoService.registrarEntrada(productoID, cantidad));
    }

    @PostMapping("/{idProducto}/salida/{cantidad}")
    public ResponseEntity<ProductoResponseDTO> registrarSalida(@PathVariable Long productoID, @PathVariable int cantidad){
        return ResponseEntity.ok(productoService.registrarSalida(productoID, cantidad));
    }






}
