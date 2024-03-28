package org.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "TDS_TB_PRODUTOCS")
public class ProdutoCS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "PRECO")
    private Double preco;

    @Column(name = "VALIDADE")
    private String validade;

    @Column(name = "TAMANHO")
    private String tamanho;

    @Column(name = "DESCRICAO")
    private String descricao;

    public ProdutoCS() {}

    public ProdutoCS(Integer id, String nome, Double preco, String validade, String tamanho, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.validade = validade;
        this.tamanho = tamanho;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoCS)) return false;
        ProdutoCS produtoCS = (ProdutoCS) o;
        return Objects.equals(getId(), produtoCS.getId()) && Objects.equals(getNome(), produtoCS.getNome()) && Objects.equals(getPreco(), produtoCS.getPreco()) && Objects.equals(getValidade(), produtoCS.getValidade()) && Objects.equals(getTamanho(), produtoCS.getTamanho()) && Objects.equals(getDescricao(), produtoCS.getDescricao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPreco(), getValidade(), getTamanho(), getDescricao());
    }
}