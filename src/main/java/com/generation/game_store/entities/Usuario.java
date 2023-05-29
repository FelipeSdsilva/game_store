package com.generation.game_store.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_usurios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo Nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo usuario é obrigatório")
    @Email(message = "O usuario vai receber um e-mail válido")
    private String usuario;

    @NotBlank(message = "O campo senha é obrigatório")
    @Size(min = 8, message = "A senha tem que ter no minimo 8 caracteres")
    private String senha;

    @Size(max = 5000, message = "O link da foto não deve ser maior que 5000 caracteres")
    private String foto;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties
    private List<Produto> produtos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Long id, String nome, String usuario, String senha, String foto) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return getId().equals(usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
