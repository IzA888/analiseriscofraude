package com.example.analiseriscofraude.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import com.example.analiseriscofraude.service.CheckoutService;
import com.example.analiseriscofraude.producer.events.PedidoCriadoEvent;

public class PedidoCriadoProducer {

    @Autowired
    private CheckoutService service;

    // exemplo usando o KAFKA para mensageria
    // @KafkaListener(
    //      topics = "pedidos-topic", 
    //      groupId="inventario-group",
    //      properties = {spring.json.value.deafult.type = PedidoCriadoEvent}) // conversão de classe
    // public void escutarPedidoCriado(PedidoCriadoEvent pedidoEvent) {
    //     system.out.println("Pedido recebido do kafka: " + pedidoEvent);
    // }
    
    @EventListener
    public void pedidoCriado(PedidoCriadoEvent pedidoEvent) {
        service.validarPagamento(pedidoEvent);
        service.atualizarEstoque(pedidoEvent);
        service.enviarEmail(pedidoEvent);
    }
}
