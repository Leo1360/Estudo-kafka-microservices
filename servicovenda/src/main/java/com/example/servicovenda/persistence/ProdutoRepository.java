package com.example.servicovenda.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicovenda.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
