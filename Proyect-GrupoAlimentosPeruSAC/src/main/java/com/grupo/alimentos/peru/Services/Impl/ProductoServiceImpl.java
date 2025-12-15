package com.grupo.alimentos.peru.Services.Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grupo.alimentos.peru.DTOs.ProductoRequestDTO;
import com.grupo.alimentos.peru.DTOs.ProductoResponseDTO;
import com.grupo.alimentos.peru.Entities.Categoria;
import com.grupo.alimentos.peru.Entities.Producto;
import com.grupo.alimentos.peru.Entities.Tienda;
import com.grupo.alimentos.peru.Exceptions.BusinessRuleException;
import com.grupo.alimentos.peru.Exceptions.ResourceNotFoundException;
import com.grupo.alimentos.peru.Mapper.ProductoMapper;
import com.grupo.alimentos.peru.Repositories.CategoriaRepository;
import com.grupo.alimentos.peru.Repositories.ProductoRepository;
import com.grupo.alimentos.peru.Repositories.TiendaRepository;
import com.grupo.alimentos.peru.Services.ProductoService;
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
        .orElseThrow(() -> new ResourceNotFoundException("La tienda no existe"));    
        Categoria categoria = categoriaRepository.findById(nuevoProducto.getIdCategoria())
        .orElseThrow(()-> new ResourceNotFoundException("No existe esta categoria"));
        if(productoRepository.existsByNombreProductoIgnoreCaseAndTienda_IdTienda(nuevoProducto.getNombreProducto(),nuevoProducto.getIdTienda()))
        {
            throw new BusinessRuleException("Ya existe este producto mano");
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
            .orElseThrow(() -> new ResourceNotFoundException("producto no existe"));
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
            .orElseThrow(()-> new ResourceNotFoundException("No existe el producto"));
        if(productoStock.getStock() > 0 ){
            throw new BusinessRuleException("Existen " + productoStock.getStock() + " productos en stock: No se puede eliminar");
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
        .orElseThrow(()->new ResourceNotFoundException("No existe el producto"));
        Tienda tienda = tiendaRepository.findById(actualizarProducto.getIdTienda())
        .orElseThrow(()->new ResourceNotFoundException("No existe la Tienda"));
        Categoria categoria = categoriaRepository.findById(actualizarProducto.getIdCategoria())
        .orElseThrow(()->new ResourceNotFoundException("No existe la categoria"));
        boolean duplicado = productoRepository.existsByNombreProductoIgnoreCaseAndTienda_IdTienda
        (actualizarProducto.getNombreProducto(), actualizarProducto.getIdTienda());

        // parte complicada
        if( duplicado && !producto.getNombreProducto().equalsIgnoreCase(actualizarProducto.getNombreProducto())){
            throw new BusinessRuleException("ya existe un producto con ese nombre en esta tienda");
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
            throw new BusinessRuleException("cantidad invalida");
        }
           Producto producto = productoRepository.findById(idProducto)
           .orElseThrow(() -> new ResourceNotFoundException("producto no encontrado"));
        producto.setStock(producto.getStock() + cantidad);
        return productoMapper.toDTO(productoRepository.save (producto));
    }

    @Override
    public ProductoResponseDTO registrarSalida(Long idProducto, int cantidad) {
        if (cantidad <= 0 ){
            throw new BusinessRuleException("cantidad no permitida");
        }
        Producto producto = productoRepository.findById(idProducto)
        .orElseThrow(()-> new ResourceNotFoundException("No se encuentra el producto"));
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
                .orElseThrow(()-> new ResourceNotFoundException("No existe esa categoria para ese producto"));  
            Tienda tienda = tiendaRepository.findById(productos.getIdTienda())
                .orElseThrow(()-> new ResourceNotFoundException("No existe esa tienda para ese producto"));
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
