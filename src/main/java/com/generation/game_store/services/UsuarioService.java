package com.generation.game_store.services;

import com.generation.game_store.dto.UsuarioDTO;
import com.generation.game_store.dto.UsuarioLogin;
import com.generation.game_store.entities.Usuario;
import com.generation.game_store.repositories.UsuarioRepository;
import com.generation.game_store.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<UsuarioDTO> listarTodosOsUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::new).toList();
    }

    public Optional<UsuarioDTO> cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(new UsuarioDTO(usuarioRepository.save(usuario)));
    }

    public Optional<UsuarioDTO> atualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId()) != usuario.getId())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe", null);

            usuario.setSenha(criptografarSenha(usuario.getSenha()));
            return Optional.ofNullable(new UsuarioDTO(usuarioRepository.save(usuario)));
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {
        var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha());

        Authentication authentication = authenticationManager.authenticate(credenciais);

        if (authentication.isAuthenticated()) {
            Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

            if (usuario.isPresent()) {
                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setToken(gerarToken(usuario.get().getUsuario()));
                usuarioLogin.get().setSenha("");

                return usuarioLogin;
            }

        }
        return Optional.empty();
    }

    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    private String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }

    public Optional<UsuarioDTO> findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return Optional.of(new UsuarioDTO(usuario));
    }
}
