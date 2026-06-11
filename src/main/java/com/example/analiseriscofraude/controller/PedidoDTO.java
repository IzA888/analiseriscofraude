package com.example.analiseriscofraude.controller;

public record PedidoDTO(
    String id, 
    String nomeCliente, 
    String emailCliente, 
    String nomeProduto, 
    String descricaoProduto, 
    double valor
) {

    public String getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public double getValor() {
        return valor;
    }

}
