package com.generation.game_store.services;

import com.generation.game_store.dto.ProdutoDTO;
import com.generation.game_store.entities.Categoria;
import com.generation.game_store.entities.Produto;
import com.generation.game_store.repositories.CategoriaRepository;
import com.generation.game_store.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<ProdutoDTO> mostrarPorPagina(Pageable pageable) {
        return produtoRepository.findAll(pageable).map(ProdutoDTO::new);
    }

    @Transactional(readOnly = true)
    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO inserirNovoProduto(ProdutoDTO dto) {
        Produto produto = new Produto();
        coverterDtoEmEntitidade(dto, produto);
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.getReferenceById(id);
        coverterDtoEmEntitidade(dto, produto);
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    public List<ProdutoDTO> precosEntreValores(Double precoInicial, Double precoFinal){
        return produtoRepository.searchByPreco(precoInicial,precoFinal).stream().map(ProdutoDTO::new).toList();
    }

    public void deletarProdutoPorId(Long id) {
        produtoRepository.deleteById(id);
    }

    private void coverterDtoEmEntitidade(ProdutoDTO dto, Produto produto) {
        produto.setName(dto.getName());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setImgUrl(dto.getImgUrl());
        produto.setPlatafor(dto.getPlatafor());

        Categoria categoria = new Categoria();

        if (dto.getCategoria().getId() != null) {
            categoria.setTipo(dto.getCategoria().getTipo());
            categoria.setDescricao(dto.getCategoria().getDescricao());
            categoriaRepository.save(categoria);
            produto.setCategoria(categoria);
        }
    }
}
