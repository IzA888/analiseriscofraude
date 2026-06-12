package com.example.analiseriscofraude.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.analiseriscofraude.producer.events.PedidoCriadoEvent;

@Controller
public class CheckoutController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @PostMapping("/checkout")
    public void pedidoCriadoEvent(PedidoDTO pedido) {
        PedidoCriadoEvent pedidoEvent = new PedidoCriadoEvent(
            pedido.getId(),
            pedido.getNomeCliente(),
            pedido.getEmailCliente(),
            pedido.getNomeProduto(),
            pedido.getDescricaoProduto(),
            pedido.getPagamento(),
            LocalDateTime.now()
        );

        eventPublisher.publishEvent(pedidoEvent);
    }
}
