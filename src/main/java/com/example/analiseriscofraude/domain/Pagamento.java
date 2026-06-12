package com.example.analiseriscofraude.domain;

import com.example.analiseriscofraude.domain.MetodoPagamentoEnum;
import com.example.analiseriscofraude.domain.StatusPagamentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagamento {
    private double valor;
    private MetodoPagamentoEnum metodo;
    private StatusPagamentoEnum status;
}