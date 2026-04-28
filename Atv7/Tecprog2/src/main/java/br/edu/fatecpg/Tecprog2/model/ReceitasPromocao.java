package br.edu.fatecpg.Tecprog2.model;

import jakarta.persistence.*;

@Entity
@Table(name="tb_receitas_promocao")
public class ReceitasPromocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String categoria;
    private boolean emPromocao;

    public ReceitasPromocao() {
    }

    public ReceitasPromocao(String nome, boolean emPromocao, String categoria) {
        this.nome = nome;
        this.emPromocao = emPromocao;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isEmPromocao() {
        return emPromocao;
    }

    public void setEmPromocao(boolean emPromocao) {
        this.emPromocao = emPromocao;
    }

    @Override
    public String toString() {
        return "Receitas_Promocao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", emPromocao=" + emPromocao + '}';
    }
}
