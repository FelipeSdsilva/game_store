package com.generation.game_store.repositories;

import com.generation.game_store.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    
}
