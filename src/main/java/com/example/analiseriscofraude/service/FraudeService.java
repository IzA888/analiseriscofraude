package com.example.analiseriscofraude.service;

@Service
public class FraudeService {

    @EventListener
    public void pedidoCriado(PedidoCriadoEvent pedidoEvent) {
        // fazer anláise de fraude aqui
    }

    validarPagamento(){
        // TODO implementação da lógica de validação de pagamento
    }

    isFraude() {
        // TODO implementação da lógica para determinar se a transação é fraude ou não
    }
}
