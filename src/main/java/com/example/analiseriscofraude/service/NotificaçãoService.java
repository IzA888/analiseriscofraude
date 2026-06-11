package com.example.analiseriscofraude.service;

public class NotificaçãoService {

    @EventListener
    public void pedidoCriado(PedidoCriadoEvent pedidoEvent) {
        // enviar e-mail com detalhes dp pedido e avisar que está pronto
    }

    enviarEmail(){
        // TODO implementação da lógica para enviar um email de notificação ao cliente
    }
}
