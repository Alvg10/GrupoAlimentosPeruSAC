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
 ProductoResponseDTO actualizarProducto (Long idProducto, ProductoRequestDTO ProductoDTO);
 void eliminarProducto (Long idProducto);
 ProductoResponseDTO aumentarStock(Long idProducto, int cantidad);
 ProductoResponseDTO disminuirStock(Long idProducto, int cantidad);


}