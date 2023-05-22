package com.generation.game_store.repositories;

import com.generation.game_store.entities.Categoria;
import com.generation.game_store.projections.CategoriaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(nativeQuery = true, value = """
            SELECT * 
            FROM tb_categoria 
            WHERE tb_categoria.tipo LIKE CONCAT('%',:tipo,'%') 
            """)
    public List<CategoriaProjection> categoriaByTipo(String tipo);
}
