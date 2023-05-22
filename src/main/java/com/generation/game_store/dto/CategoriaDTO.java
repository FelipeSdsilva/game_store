package com.generation.game_store.dto;

import com.generation.game_store.entities.Categoria;
import com.generation.game_store.projections.CategoriaProjection;
import org.springframework.beans.BeanUtils;

public class CategoriaDTO {
    private Long id;
    private String tipo;
    private String descricao;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public CategoriaDTO(CategoriaProjection projection) {
        id = projection.getId();
        tipo = projection.getTipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
