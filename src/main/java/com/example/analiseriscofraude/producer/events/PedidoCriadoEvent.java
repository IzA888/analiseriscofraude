package com.example.analiseriscofraude.producer.events;

import com.example.analiseriscofraude.domain.Pagamento;
import java.time.LocalDateTime;


public record PedidoCriadoEvent(
    String id,
    String nomeCliente,
    String emailCliente,
    String nomeProduto,
    String descricaoProduto,
    Pagamento pagamento,
    LocalDateTime criadoEm
) {
    
    @Override
    public String toString() {
        return "PedidoCriadoEvent{" +
                "id='" + getId() + '\'' +
                ", nomeCliente='" + getNomeCliente() + '\'' +
                ", emailCliente='" + getEmailCliente() + '\'' +
                ", nomeProduto='" + getNomeProduto() + '\'' +
                ", descricaoProduto='" + getDescricaoProduto() + '\'' +
                ", valor='" + pagamento.getValor() + '\'' +
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

    public Pagamento getPagamento() {
        return pagamento;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}

