package com.grupo.alimentos.peru.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.grupo.alimentos.peru.dto.ProductoRequestDTO;
import com.grupo.alimentos.peru.dto.ProductoResponseDTO;
import com.grupo.alimentos.peru.services.ProductoService;

import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping("/api/productos")
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

    @PostMapping("/porLote")
    public ResponseEntity<List<ProductoResponseDTO>> crearProductoPorLote (@RequestBody List<ProductoRequestDTO> porLotes){
        List<ProductoResponseDTO> productosXLote = productoService.crearProductos(porLotes);
        return ResponseEntity.status(HttpStatus.CREATED).body(productosXLote);
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorID(@PathVariable Long idProducto){
        return ResponseEntity.ok(productoService.obtenerProductosPorID(idProducto));
    }

    @GetMapping()
    public ResponseEntity<List<ProductoResponseDTO>> listaTodo (){
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductoPorCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok(productoService.listarProductoPorCategoria(idCategoria));
    }


    @GetMapping("/tienda/{idTienda}")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductoPorTienda(@PathVariable Long idTienda){
        return ResponseEntity.ok(productoService.listarProductosPorTienda(idTienda));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombre(@RequestParam String nombre){      //  http://localhost:8080/api/productos/buscar?nombre=chicha
        return ResponseEntity.ok(productoService.buscarProductoPorCoincidencia(nombre));
    }


    @PutMapping("/{IdProduct}")
    public ResponseEntity<ProductoResponseDTO>actualizarProducto(@PathVariable Long IdProduct,@Valid @RequestBody ProductoRequestDTO actualizarProduct){
        return ResponseEntity.ok(productoService.actualizarProducto(IdProduct, actualizarProduct));
            }

    @DeleteMapping("/{productoID}")
    public ResponseEntity<Void> eliminarProducto (@PathVariable Long productoID){
        productoService.eliminarProducto(productoID);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{productoID}/entrada/{cantidad}")
    public ResponseEntity<ProductoResponseDTO> registrarEntrada(@PathVariable Long productoID, @PathVariable int cantidad){
        return ResponseEntity.ok(productoService.registrarEntrada(productoID, cantidad));
    }

    @PostMapping("/{productoID}/salida/{cantidad}")
    public ResponseEntity<ProductoResponseDTO> registrarSalida(@PathVariable Long productoID, @PathVariable int cantidad){
        return ResponseEntity.ok(productoService.registrarSalida(productoID, cantidad));
    }






}
