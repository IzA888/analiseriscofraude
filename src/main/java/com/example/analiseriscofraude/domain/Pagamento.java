package com.example.analiseriscofraude.domain;

import com.example.analiseriscofraude.domain.enums.MetodoPagamentoEnum;
import com.example.analiseriscofraude.domain.enums.StatusPagamentoEnum;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Pagamento {

    private double valor;

    private MetodoPagamentoEnum metodo;

    private StatusPagamentoEnum status;

}