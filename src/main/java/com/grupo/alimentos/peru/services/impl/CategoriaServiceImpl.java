package com.grupo.alimentos.peru.services.impl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.grupo.alimentos.peru.dto.CategoriaRequestDTO;
import com.grupo.alimentos.peru.dto.CategoriaResponseDTO;
import com.grupo.alimentos.peru.entity.Categoria;
import com.grupo.alimentos.peru.exception.AlreadyExistsException;
import com.grupo.alimentos.peru.exception.ResourceNotFoundException;
import com.grupo.alimentos.peru.mapper.CategoriaMapper;
import com.grupo.alimentos.peru.repository.CategoriaRepository;
import com.grupo.alimentos.peru.services.CategoriaService;
import com.grupo.alimentos.peru.util.Messages;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;


    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO categoriaRequestDto)  {        
        if(categoriaRepository.existsByNombreCategoriaIgnoreCase(categoriaRequestDto.getNombreCategoria())){
             throw new AlreadyExistsException (Messages.CATEGORY_ALREADY_EXISTS);}   
            
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
            .orElseThrow(() -> new ResourceNotFoundException(Messages.CATEGORY_NOT_FOUND_ID));
            return categoriaMapper.toDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO actualizarCategoria(Long idCategoriaActualiza, CategoriaRequestDTO categoriaDTO) {        
        Categoria categoriaExsitente = categoriaRepository.findById(idCategoriaActualiza)
            .orElseThrow(() ->  new ResourceNotFoundException(Messages.CATEGORY_NOT_FOUND_UPDATE));        
        categoriaExsitente.setNombreCategoria(categoriaDTO.getNombreCategoria());
        categoriaExsitente.setDescripcionCategoria(categoriaDTO.getDescripcionCategoria());
        Categoria nombreCategoria = categoriaRepository.save(categoriaExsitente);
        return categoriaMapper.toDTO(nombreCategoria);
    }   



    @Override
    public void eliminarCategoria(Long idCategoria) {
       Categoria categoria = categoriaRepository.findById(idCategoria)
        .orElseThrow(() -> new ResourceNotFoundException(Messages.CATEGORY_NOT_FOUND)); 
       categoriaRepository.delete(categoria);
    }

    
    @Override
    public CategoriaResponseDTO obtenerCategoriaPorNombre(String nombreCategoria) {
        Categoria nombreExsitente = categoriaRepository.findByNombreCategoriaIgnoreCase(nombreCategoria)
            .orElseThrow(() ->  new ResourceNotFoundException(Messages.CATEGORY_NOT_FOUND_ID));
            return categoriaMapper.toDTO(nombreExsitente);

    }    

}
