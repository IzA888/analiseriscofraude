package com.example.analiseriscofraude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.analiseriscofraude.producer.events.PedidoCriadoEvent;

public interface EstoqueRepository extends JpaRepository<Long, PedidoCriadoEvent>{

}
