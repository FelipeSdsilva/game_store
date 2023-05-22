package com.generation.game_store.repositories;

import com.generation.game_store.entities.Produto;
import com.generation.game_store.projections.ProdutoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_produto.id, tb_produto.name, tb_produto.preco, " +
			"tb_produto.img_url AS imgUrl," +
			"tb_produto.descricao, tb_produto.platafor "+
			"FROM tb_produto " +
			"WHERE tb_produto.preco BETWEEN :precoInicial AND :precoFinal ")
    List<ProdutoProjection> searchByPreco(Double precoInicial, Double precoFinal);
}
