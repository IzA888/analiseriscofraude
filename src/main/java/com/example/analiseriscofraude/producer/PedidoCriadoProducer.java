package com.example.analiseriscofraude.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.analiseriscofraude.service.CheckoutService;

import lombok.extern.slf4j.Slf4j;

import com.example.analiseriscofraude.producer.events.PedidoCriadoEvent;

@Slf4j
@Component
public class PedidoCriadoProducer {

    @Autowired
    private CheckoutService service;

    // @Autowired
    // private KafkaTemplate<String, Object> kafkaTemplate;

    @EventListener
    public void pedidoCriado(PedidoCriadoEvent pedidoEvent) {
        String resultadoPag = service.validarPagamento(pedidoEvent);
        if (resultadoPag.contains("aprovado")) {
            service.atualizarEstoque(pedidoEvent);
            service.enviarEmail(pedidoEvent);
        } else {
            log.error("Pagamento inválido, pedido: " + pedidoEvent.getId());
        }
    }

    // exemplo usando o KAFKA para mensageria
    // @KafkaListener(
    //      topics = "pedido-criado-topic", 
    //      groupId="finaceiro-group",
    //      properties = {spring.json.value.deafult.type = PedidoCriadoEvent}) // conversão de classe
    // public void processarPagamento(PedidoCriadoEvent pedidoEvent) {
    //     system.out.println("Pedido recebido do kafka: " + pedidoEvent);
    //      String resultado = service.validarPagamento(pedidoEvent);
    //      if(resultado.contains("aprovado")) {
    //         kafkaTemplate.send("pagameto-aprovado-topic", pedidoEvent);
    //     } else {
    //         log.error("Pagamento rejeitado para o pedido: " + pedidoEvent.getId());
    //         kafkaTemplate.send("pagamento-cancelado-topic", pedidoEvent);
    //     }
    // }
    
    // @KafkaListener(
    //      topics = "pagamento-aprovado-topic", 
    //      groupId="estoque-group",
    //      properties = {spring.json.value.deafult.type = PedidoCriadoEvent}) // conversão de classe
    // public void atualizarEstoque(PedidoCriadoEvent pedidoEvent) {
    //     system.out.println("Pedido recebido do kafka: " + pedidoEvent);
    //     service.atualizarEstoque(pedidoEvent);
    //     KafkaTemplate.send("estoque-atualizado-topic", pedidoEvent);
    // }
    
    // @KafkaListener(
    //      topics = "estoque-atualizado-topic", 
    //      groupId="notificacao-group",
    //      properties = {spring.json.value.deafult.type = PedidoCriadoEvent}) // conversão de classe
    // public void enviarEmailConfirmacao(PedidoCriadoEvent pedidoEvent) {
    //     system.out.println("Pedido recebido do kafka: " + pedidoEvent);
    //     service.enviarEmail(pedidoEvent);
    //     log.info("Email enviado para o cliente do pedido: " + pedidoEvent.getId());
    // }
}
