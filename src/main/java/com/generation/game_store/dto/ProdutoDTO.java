package com.generation.game_store.dto;

import com.generation.game_store.entities.Produto;
import com.generation.game_store.projections.ProdutoProjection;

public class ProdutoDTO {
    private Long id;
    private String name;
    private Double preco;
    private String descricao;
    private String imgUrl;
    private String platafor;
    private CategoriaDTO categoria;

    public ProdutoDTO(Long id, String name, Double preco, String descricao, String imgUrl, String platafor, CategoriaDTO categoria) {
        this.id = id;
        this.name = name;
        this.preco = preco;
        this.descricao = descricao;
        this.imgUrl = imgUrl;
        this.platafor = platafor;
        this.categoria = categoria;
    }

    public ProdutoDTO(Produto entity) {
        id = entity.getId();
        name = entity.getName();
        preco = entity.getPreco();
        descricao = entity.getDescricao();
        imgUrl = entity.getImgUrl();
        platafor = entity.getPlatafor();
        categoria = new CategoriaDTO(entity.getCategoria());
    }

    public ProdutoDTO(ProdutoProjection projection) {
        id = projection.getId();
        name = projection.getName();
        preco = projection.getPreco();
        descricao = projection.getDescricao();
        imgUrl = projection.getImgUrl();
        platafor = projection.getPlatafor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String desc) {
        this.descricao = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPlatafor() {
        return platafor;
    }

    public void setPlatafor(String platafor) {
        this.platafor = platafor;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}
