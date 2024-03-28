package org.example;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private double preco;
    private String validade;
    private String tamanho;
    private String descricao;

    public Produto(int id, String nome, double preco, String validade, String tamanho, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.validade = validade;
        this.tamanho = tamanho;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
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

}
