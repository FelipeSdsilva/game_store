package com.generation.game_store.controllers;

import com.generation.game_store.dto.ProdutoDTO;
import com.generation.game_store.services.ProdutoService;
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
@RequestMapping(value = "/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> getAllProdutcsPerPage(Pageable pageable) {
        return ResponseEntity.ok().body(produtoService.mostrarPorPagina(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.buscarPorId(id));
    }

    @GetMapping(value = "/{precoInicial}/{precoFinal}")
    public ResponseEntity<List<ProdutoDTO>> buscarEntrePrecos(@PathVariable String precoInicial, @PathVariable String precoFinal) {
        return ResponseEntity.ok().body(produtoService.precosEntreValores(Double.parseDouble(precoInicial), Double.parseDouble(precoFinal)));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insertNewProduct(@Valid @RequestBody ProdutoDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(produtoService.inserirNovoProduto(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> updateProduct(@PathVariable Long id,@Valid @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok().body(produtoService.atualizarProduto(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        produtoService.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
