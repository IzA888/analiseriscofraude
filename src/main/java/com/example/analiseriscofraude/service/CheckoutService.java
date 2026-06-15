package com.example.analiseriscofraude.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.analiseriscofraude.repository.EstoqueRepository;
import com.example.analiseriscofraude.producer.events.PedidoCriadoEvent;
import com.example.analiseriscofraude.domain.Pagamento;
import com.example.analiseriscofraude.domain.Pedido;
import com.example.analiseriscofraude.domain.enums.StatusPagamentoEnum;



@Service
public class CheckoutService {
    
    @Autowired
    private EstoqueRepository repo;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private OllamaChatModel chatModel;

    @Transactional
    public String atualizarEstoque(PedidoCriadoEvent pe) {
        Pedido pedido = new Pedido();
        pedido.setNomeProduto(pe.getNomeProduto());
        pedido.setDescricaoProduto(pe.getDescricaoProduto());
        pedido.setPagamento(pe.getPagamento());
        repo.save(pedido);
        return "Estoque atualizado, pedido: " + pe.getId();
    }

    public String validarPagamento(PedidoCriadoEvent pe){
        if (pe.getPagamento().getValor() == 0.0){
            pe.getPagamento().setStatus(StatusPagamentoEnum.REJEITADO);
            return "Pagamento inválido para o pedido: " + pe.getId();
        }

        isFraude(pe.getPagamento());

        return "Pagamento aprovado para o pedido" + pe.getId();        
    }

    private void isFraude(Pagamento pagamento) {
        String prompt = """
                Analise a seguinte tentativa de compra e responda apenas com 'SEGURO' ou 'SUSPEITO'.
                Dados do comprador:
                - historico de compras canceladas: %d
                - valor da compra atual: R$ %.2f
                - localizacao da compra: %s
                - localizacao do cadastro: %s
                """.formatted(getComprasCanceladas(), pagamento.getValor(), getLocalCompra(), getLocalCadastro());

        String analiseRisco = chatModel.call(prompt).trim();

        if("SUSPEITO".equalsIgnoreCase(analiseRisco)) {
            pagamento.setStatus(StatusPagamentoEnum.RETIDO);
        } else {
            pagamento.setStatus(StatusPagamentoEnum.VALIDO);
        }
    }
    
    public String enviarEmail(PedidoCriadoEvent pe){
        String texto = pe.toString();
        
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("seupedidoaprovado@gmail.com");
        mensagem.setTo(pe.getEmailCliente());
        mensagem.setSubject("CONFIRA OS DADOS DO SEU PEDIDO");
        mensagem.setText(texto);
        
        mailSender.send(mensagem);
        return "Email enviado para: " + pe.getEmailCliente();
    }

    private int getComprasCanceladas() {
        // simula um histórico de compras canceladas entre 0 e 4
        return ThreadLocalRandom.current().nextInt(0, 5);
    }

    private String getLocalCompra() {
        // simula possíveis localidades de compra
        String[] locais = {"desconhecida", "Brasil", "Estados Unidos", "Portugal", "Argentina"};
        return locais[ThreadLocalRandom.current().nextInt(locais.length)];
    }

    private String getLocalCadastro() {
        // simula possíveis localidades de cadastro
        String[] locais = {"desconhecida", "Brasil", "Estados Unidos", "Portugal", "Argentina"};
        return locais[ThreadLocalRandom.current().nextInt(locais.length)];
    }
}
