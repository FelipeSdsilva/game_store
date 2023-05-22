package com.generation.game_store.services;

import com.generation.game_store.dto.CategoriaDTO;
import com.generation.game_store.entities.Categoria;
import com.generation.game_store.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<CategoriaDTO> mostrarPorPagina(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(CategoriaDTO::new);
    }

    @Transactional(readOnly = true)
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO inserirNovoCategoria(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        coverterDtoEmEntitidade(dto, categoria);
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO dto){
        Categoria categoria = categoriaRepository.getReferenceById(id);
        coverterDtoEmEntitidade(dto,categoria);
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);
    }


    public void deletarCategoriaPorId(Long id){
        categoriaRepository.deleteById(id);
    }

    private void coverterDtoEmEntitidade(CategoriaDTO dto, Categoria categoria) {
        categoria.setTipo(dto.getTipo());
        categoria.setDescricao(dto.getDescricao());
    }

    public List<CategoriaDTO> buscarPorTipo(String tipo) {
        return categoriaRepository.categoriaByTipo(tipo).stream().map(CategoriaDTO::new).toList();
    }
}
