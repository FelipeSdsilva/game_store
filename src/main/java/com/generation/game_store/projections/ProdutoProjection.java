package com.generation.game_store.projections;

public interface ProdutoProjection {
    Long getId();
    String getName();
    Double getPreco();
    String getDescricao();
    String getImgUrl();
    String getPlatafor();
}
