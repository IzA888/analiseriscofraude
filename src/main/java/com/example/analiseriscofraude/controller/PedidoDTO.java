package com.example.analiseriscofraude.controller;

import com.example.analiseriscofraude.domain.Pagamento;

public record PedidoDTO(
    String id, 
    String nomeCliente, 
    String emailCliente, 
    String nomeProduto, 
    String descricaoProduto, 
    Pagamento pagamento
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

    public Pagamento getPagamento() {
        return pagamento;
    }
}
