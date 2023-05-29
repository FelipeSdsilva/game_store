package com.generation.game_store.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size
    private String name;

    @NotNull
    @Positive
    private Double preco;

    @NotBlank
    @Size
    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NotBlank
    @Size
    private String imgUrl;

    @NotBlank
    @Size
    private String platafor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("usuario")
    private Usuario usuario;


    public Produto() {
    }

    public Produto(Long id, String name, Double preco, String descricao, String imgUrl, String platafor, Categoria categoria) {
        this.id = id;
        this.name = name;
        this.preco = preco;
        this.descricao = descricao;
        this.imgUrl = imgUrl;
        this.platafor = platafor;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return getId().equals(produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

