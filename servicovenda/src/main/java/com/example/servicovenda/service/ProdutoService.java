package com.example.servicovenda.service;

import org.springframework.stereotype.Service;

import com.example.servicovenda.exceptions.BadRequestException;
import com.example.servicovenda.model.Produto;
import com.example.servicovenda.persistence.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repo;

    public Long save(Produto produto){
        return repo.save(produto).getId();
    }

    public Produto findById(Long id){
        return repo.findById(id).orElseThrow(() -> new BadRequestException("prodProduto não localizado"));
    }

    public void update(Produto produto, Long id){
        findById(id);
        produto.setId(id);
        save(produto);
    }

    public void delete(Long id){
        findById(id);
        repo.deleteById(id);
    }

}
