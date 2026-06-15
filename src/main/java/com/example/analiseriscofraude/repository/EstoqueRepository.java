package com.example.analiseriscofraude.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.analiseriscofraude.domain.Pedido;

public interface EstoqueRepository extends JpaRepository<Pedido, Long>{

}
