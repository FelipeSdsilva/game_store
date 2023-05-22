package com.generation.game_store.controllers;

import com.generation.game_store.dto.CategoriaDTO;
import com.generation.game_store.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> getAllCategoriesPerPage(Pageable pageable) {
        return ResponseEntity.ok().body(categoriaService.mostrarPorPagina(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriatById(@PathVariable Long id){
        return ResponseEntity.ok().body(categoriaService.buscarPorId(id));
    }

    @GetMapping(value = "/tipos/{tipo}")
    public ResponseEntity<List<CategoriaDTO>> getPerType(@PathVariable String tipo){
        return ResponseEntity.ok().body(categoriaService.buscarPorTipo(tipo));
    }


    @PostMapping
    public ResponseEntity<CategoriaDTO> insertNewProduct(@Valid @RequestBody CategoriaDTO dto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(categoriaService.inserirNovoCategoria(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto){
        return ResponseEntity.ok().body(categoriaService.atualizarCategoria(id,dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
