package com.grupo.alimentos.peru.Services.Impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.grupo.alimentos.peru.DTOs.CategoriaRequestDTO;
import com.grupo.alimentos.peru.DTOs.CategoriaResponseDTO;
import com.grupo.alimentos.peru.Entities.Categoria;
import com.grupo.alimentos.peru.Exceptions.AlreadyExistsException;
import com.grupo.alimentos.peru.Exceptions.ResourceNotFoundException;
import com.grupo.alimentos.peru.Mapper.CategoriaMapper;
import com.grupo.alimentos.peru.Repositories.CategoriaRepository;
import com.grupo.alimentos.peru.Services.CategoriaService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;


    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO categoriaRequestDto)  {        
        if(categoriaRepository.existsByNombreCategoriaIgnoreCase(categoriaRequestDto.getNombreCategoria())){
             throw new AlreadyExistsException ("Parece que la categoria ya existe ;)");}   
            
        /* Categoria nuevaCategoria = categoriaMapper.toEntity(categoriaRequestDto);     
        Categoria categoria = categoriaRepository.save(nuevaCategoria);
        return categoriaMapper.toDTO(categoria); */
        return categoriaMapper.toDTO(categoriaRepository.save(categoriaMapper.toEntity(categoriaRequestDto)));  
    }
    @Override
    public List<CategoriaResponseDTO> listarCategoria() {
        List<Categoria> listaCategoria = categoriaRepository.findAll();
       // List<CategoriaResponseDTO> listaCategoriaDTO = categoriaMapper.toDTO(listaCategoria);  << Error
       return listaCategoria.stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }    
    
    @Override
    public CategoriaResponseDTO obtenerCategoriaPorID(Long idcateg) {
        /* if(categoriaRepository.existsById(idcateg) == false ){
            throw new ResourceNotFoundException ("La categoria no existe");
        }   */     

            Categoria categoria = categoriaRepository.findById(idcateg)
            .orElseThrow(() -> new ResourceNotFoundException("Categoria No Existeee"));
            return categoriaMapper.toDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO actualizarCategoria(Long idCategoriaActualiza, CategoriaRequestDTO categoriaDTO) {
        
        Categoria categoriaExsitente = categoriaRepository.findById(idCategoriaActualiza)
            .orElseThrow(() ->  new ResourceNotFoundException("No se encontro la categoria lamentablemente"));        
        categoriaExsitente.setNombreCategoria(categoriaDTO.getNombreCategoria());
        categoriaExsitente.setNombreCategoria(categoriaDTO.getDescripcionCategoria());
        Categoria nombreCategoria = categoriaRepository.save(categoriaExsitente);
        return categoriaMapper.toDTO(nombreCategoria);
    }   
    @Override
    public void eliminarCategoria(Long idCategoria) {
       Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
       if(!categoria.isPresent()){
        throw new ResourceNotFoundException("Categoria no encontrada");  
       }
       categoriaRepository.deleteById(idCategoria);
    }
    @Override
    public CategoriaResponseDTO obtenerCategoriaPorNombre(String nombreCategoria) {
        Categoria nombreExsitente = categoriaRepository.findByNombreCategoriaIgnoreCase(nombreCategoria)
            .orElseThrow(() ->  new c("No se encontro el nombre lamentablemente"));
            return categoriaMapper.toDTO(nombreExsitente);

    }    

}
