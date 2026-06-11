package com.example.analiseriscofraude.events;

import java.time.LocalDateTime;

public record PedidoCriadoEvent(
    String id,
    String nomeCliente,
    String emailCliente,
    String nomeProduto,
    String descricaoProduto,
    double valor,
    LocalDateTime criadoEm
) {
    
    
    public String toString() {
        return "PedidoCriadoEvent{" +
                "id='" + getId() + '\'' +
                ", nomeCliente='" + getNomeCliente() + '\'' +
                ", emailCliente='" + getEmailCliente() + '\'' +
                ", nomeProduto='" + getNomeProduto() + '\'' +
                ", descricaoProduto='" + getDescricaoProduto() + '\'' +
                ", valor='" + getValor() + '\'' +
                ", criadoEm='" + getCriadoEm() + '\'' +
                '}';

    }

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

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

}

