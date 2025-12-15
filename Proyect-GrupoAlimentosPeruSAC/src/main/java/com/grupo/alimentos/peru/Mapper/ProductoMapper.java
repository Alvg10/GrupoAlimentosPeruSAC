package com.grupo.alimentos.peru.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.grupo.alimentos.peru.DTOs.CategoriaResponseDTO;
import com.grupo.alimentos.peru.DTOs.ProductoRequestDTO;
import com.grupo.alimentos.peru.DTOs.ProductoResponseDTO;
import com.grupo.alimentos.peru.DTOs.TiendaResponseDTO;
import com.grupo.alimentos.peru.Entities.Producto;

@Component
public class ProductoMapper {  

/* 
    private final ModelMapper modelmapper;
    public ProductoMapper(ModelMapper modelmapper){
    this.modelmapper = modelmapper;
    this.modelmapper.getConfiguration().setSkipNullEnabled(true);
    var typeMap = this.modelmapper.createTypeMap(ProductoRequestDTO.class, Producto.class);
    typeMap.addMappings(m -> {
        m.skip(Producto::setIdProducto);      
        m.map(ProductoRequestDTO::getNombreProducto, Producto::setNombreProducto);
        m.map(ProductoRequestDTO::getPrecioProducto, Producto::setPrecioProducto);
        m.map(src -> null, Producto::setCategoria); 
        m.map(src -> null, Producto::setTienda);
        m.skip(Producto::setStock); 
    });

    }
    public ProductoResponseDTO toDTO(Producto product){
        return modelmapper.map(product, ProductoResponseDTO.class);
    }
    public Producto toEntity(ProductoRequestDTO productoRequest){
        return modelmapper.map(productoRequest, Producto.class);
    }
 */

   public ProductoResponseDTO toDTO(Producto producto) {

    ProductoResponseDTO dto = new ProductoResponseDTO();

    dto.setIdProducto(producto.getIdProducto());
    dto.setNombreProducto(producto.getNombreProducto());
    dto.setPrecioProducto(producto.getPrecioProducto());
    dto.setStock(producto.getStock());

    dto.setCategoria(
        new CategoriaResponseDTO(
            producto.getCategoria().getIdCategoria(),
            producto.getCategoria().getNombreCategoria(),
            producto.getCategoria().getDescripcionCategoria()
        )
    );

    dto.setTienda(
        new TiendaResponseDTO(
            producto.getTienda().getIdTienda(),
            producto.getTienda().getNombreTienda(),
            producto.getTienda().getDireccionTienda(),
            producto.getTienda().getDistritoTienda()
        )
    );

    return dto;
    }

    public Producto toEntity(ProductoRequestDTO dto){
        Producto producto = new Producto();
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setPrecioProducto(dto.getPrecioProducto());
        // categoria y tienda se asignan en el service
        return producto;
    }

}


// ModelMapper solo mapea bien campos simples, tuve problemas con los IDs de las otras tablas
// solucion dejar ModelMapper y hacerlo manual.
// 2do problema setCategoria y setTienda no se mapeaban bien asi que se instancio.
