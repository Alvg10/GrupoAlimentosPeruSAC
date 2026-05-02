package com.grupo.alimentos.peru.services.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo.alimentos.peru.dto.ProductoRequestDTO;
import com.grupo.alimentos.peru.dto.ProductoResponseDTO;
import com.grupo.alimentos.peru.entity.Categoria;
import com.grupo.alimentos.peru.entity.Producto;
import com.grupo.alimentos.peru.entity.Tienda;
import com.grupo.alimentos.peru.exception.BusinessRuleException;
import com.grupo.alimentos.peru.exception.ResourceNotFoundException;
import com.grupo.alimentos.peru.mapper.ProductoMapper;
import com.grupo.alimentos.peru.repository.CategoriaRepository;
import com.grupo.alimentos.peru.repository.ProductoRepository;
import com.grupo.alimentos.peru.repository.TiendaRepository;
import com.grupo.alimentos.peru.services.ProductoService;
import com.grupo.alimentos.peru.util.Messages;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    
    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;
    private final TiendaRepository tiendaRepository;
    private final CategoriaRepository categoriaRepository;  

    @Override
    public ProductoResponseDTO crearProducto(ProductoRequestDTO nuevoProducto) {
        Tienda tienda = tiendaRepository.findById(nuevoProducto.getIdTienda())
        .orElseThrow(() -> new ResourceNotFoundException(Messages.STORE_NOT_FOUND_ID));    
        Categoria categoria = categoriaRepository.findById(nuevoProducto.getIdCategoria())
        .orElseThrow(()-> new ResourceNotFoundException(Messages.PRODUCT_ALREADY_EXISTS));
        if(productoRepository.existsByNombreProductoIgnoreCaseAndTienda_IdTienda(nuevoProducto.getNombreProducto(),nuevoProducto.getIdTienda()))
        {
            throw new BusinessRuleException(Messages.PRODUCT_ALREADY_EXISTS);
        }
        Producto producto = productoMapper.toEntity(nuevoProducto);
        producto.setStock(0);
        producto.setCategoria(categoria);
        producto.setTienda(tienda);
        return productoMapper.toDTO(productoRepository.save(producto));
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerProductosPorID(Long idProducto) {
        Producto productoID = productoRepository.findById(idProducto)   
            .orElseThrow(() -> new ResourceNotFoundException(Messages.PRODUCT_NOT_FOUND));
        return productoMapper.toDTO(productoID);     
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductos() {
        List<Producto> listaProducto = productoRepository.findAll();
        return listaProducto.stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductoPorCategoria(Long idCategoria) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria)
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductosPorTienda(Long idTienda) {
        return productoRepository.findByTienda_IdTienda(idTienda)
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        
        Producto productoStock = productoRepository.findById(idProducto)
            .orElseThrow(()-> new ResourceNotFoundException(Messages.PRODUCT_NOT_FOUND));
        if(productoStock.getStock() > 0 ){
            throw new BusinessRuleException(Messages.PRODUCT_STOCK_EXISTS + productoStock.getStock());
        }
        productoRepository.delete(productoStock);

    }

    @Override
    public List<ProductoResponseDTO> buscarProductoPorCoincidencia(String nombreProducto) {
        return productoRepository.findByNombreProductoContainingIgnoreCase(nombreProducto)
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }  


    @Override
    public ProductoResponseDTO actualizarProducto(Long idProducto, ProductoRequestDTO actualizarProducto) {
        Producto producto = productoRepository.findById(idProducto)
        .orElseThrow(()->new ResourceNotFoundException(Messages.PRODUCT_NOT_FOUND));
        Tienda tienda = tiendaRepository.findById(actualizarProducto.getIdTienda())
        .orElseThrow(()->new ResourceNotFoundException(Messages.STORE_NOT_FOUND_UPDATE));
        Categoria categoria = categoriaRepository.findById(actualizarProducto.getIdCategoria())
        .orElseThrow(()->new ResourceNotFoundException(Messages.CATEGORY_NOT_FOUND_UPDATE));
        boolean duplicado = productoRepository.existsByNombreProductoIgnoreCaseAndTienda_IdTienda
        (actualizarProducto.getNombreProducto(), actualizarProducto.getIdTienda());

        // parte complicada
        if( duplicado && !producto.getNombreProducto().equalsIgnoreCase(actualizarProducto.getNombreProducto())){
            throw new BusinessRuleException(Messages.PRODUCT_DUPLICATE_UPDATE);
        }
        // No se usa productoMapper.toEntity ya que este espera un objeto y no un string
        producto.setNombreProducto(actualizarProducto.getNombreProducto());
        producto.setPrecioProducto(actualizarProducto.getPrecioProducto());
        producto.setCategoria(categoria);
        producto.setTienda(tienda);
        return productoMapper.toDTO(productoRepository.save(producto));     
    }

   
    @Override
    public ProductoResponseDTO registrarEntrada(Long idProducto, int cantidad) {
        if(cantidad <= 0){
            throw new BusinessRuleException(Messages.INVALID_QUANTITY_INPUT);
        }
           Producto producto = productoRepository.findById(idProducto)
           .orElseThrow(() -> new ResourceNotFoundException(Messages.PRODUCT_NOT_FOUND_IMPUT));
        producto.setStock(producto.getStock() + cantidad);
        return productoMapper.toDTO(productoRepository.save (producto));
    }

    @Override
    public ProductoResponseDTO registrarSalida(Long idProducto, int cantidad) {
        if (cantidad <= 0 ){
            throw new BusinessRuleException(Messages.INVALID_QUANTITY_OUTPUT);
        }
        Producto producto = productoRepository.findById(idProducto)
        .orElseThrow(()-> new ResourceNotFoundException(Messages.PRODUCT_NOT_FOUND_OUTPUT));
        if (producto.getStock() - cantidad < 0){
            throw new BusinessRuleException("no hay stock suficiente");
       }
       producto.setStock(producto.getStock() - cantidad);
       return productoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public List<ProductoResponseDTO> crearProductos(List<ProductoRequestDTO> productosVarios) {
        List<Producto> muchosProducts = new ArrayList<>();
        for (ProductoRequestDTO productos : productosVarios){
            Producto productoSnuevos = productoMapper.toEntity(productos);
            Categoria categoria = categoriaRepository.findById(productos.getIdCategoria())
                .orElseThrow(()-> new ResourceNotFoundException(Messages.CATEGORY_NOT_FOUND_ID));  
            Tienda tienda = tiendaRepository.findById(productos.getIdTienda())
                .orElseThrow(()-> new ResourceNotFoundException(Messages.STORE_NOT_FOUND_ID));
            productoSnuevos.setCategoria(categoria);
            productoSnuevos.setTienda(tienda);
            muchosProducts.add(productoSnuevos);
        }
        List<Producto> lote = productoRepository.saveAll(muchosProducts);
        return lote.stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

   
    
}
