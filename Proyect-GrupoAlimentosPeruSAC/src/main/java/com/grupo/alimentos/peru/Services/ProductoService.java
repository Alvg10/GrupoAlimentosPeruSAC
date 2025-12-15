package com.grupo.alimentos.peru.Services;

import java.util.List;

import com.grupo.alimentos.peru.DTOs.ProductoRequestDTO;
import com.grupo.alimentos.peru.DTOs.ProductoResponseDTO;

public interface ProductoService {
 ProductoResponseDTO crearProducto (ProductoRequestDTO producto);
 ProductoResponseDTO obtenerProductosPorID (Long idProducto);
 List<ProductoResponseDTO> listarProductos();
 List<ProductoResponseDTO> listarProductoPorCategoria(Long idCategoria);
 List<ProductoResponseDTO> listarProductosPorTienda(Long idTienda);
 List<ProductoResponseDTO> buscarProductoPorCoincidencia(String nombreProducto);
 ProductoResponseDTO actualizarProducto (Long idProducto, ProductoRequestDTO actualizarProducto);
 void eliminarProducto (Long idProducto);
 ProductoResponseDTO registrarEntrada(Long idProducto, int cantidad);
 ProductoResponseDTO registrarSalida(Long idProducto, int cantidad);
 List<ProductoResponseDTO> crearProductos (List<ProductoRequestDTO> productosVarias);


}