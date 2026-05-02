package com.grupo.alimentos.peru.mapper;
import org.springframework.stereotype.Component;
import com.grupo.alimentos.peru.dto.CategoriaResponseDTO;
import com.grupo.alimentos.peru.dto.ProductoRequestDTO;
import com.grupo.alimentos.peru.dto.ProductoResponseDTO;
import com.grupo.alimentos.peru.dto.TiendaResponseDTO;
import com.grupo.alimentos.peru.entity.Categoria;
import com.grupo.alimentos.peru.entity.Producto;
import com.grupo.alimentos.peru.entity.Tienda;
import com.grupo.alimentos.peru.exception.BusinessRuleException;
import com.grupo.alimentos.peru.util.Messages;

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

    

    if(producto.getCategoria() == null){
        throw new BusinessRuleException(Messages.PRODUCT_NOT_CATEGORY_ASSIGNED + producto.getIdProducto() );
    }
    Categoria catgr = producto.getCategoria();
        dto.setCategoria(new CategoriaResponseDTO(
            catgr.getIdCategoria(),
            catgr.getNombreCategoria(),
            catgr.getDescripcionCategoria()
        ));

    if (producto.getTienda() == null) {
        throw new BusinessRuleException(Messages.PRODUCT_NOT_STORE_ASSIGNED + producto.getIdProducto());
    }
        Tienda tienda = producto.getTienda();
        dto.setTienda(new TiendaResponseDTO(
            tienda.getIdTienda(),
            tienda.getNombreTienda(),
            tienda.getDireccionTienda(),
            tienda.getDistritoTienda()
        ));

        /* dto.setCategoria(
        new CategoriaResponseDTO(
            producto.getCategoria().getIdCategoria(),
            producto.getCategoria().getNombreCategoria(),
            producto.getCategoria().getDescripcionCategoria()
        )
        );*/


        /* dto.setTienda(
        new TiendaResponseDTO(
            producto.getTienda().getIdTienda(),
            producto.getTienda().getNombreTienda(),
            producto.getTienda().getDireccionTienda(),
            producto.getTienda().getDistritoTienda()
        )
        );*/

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
