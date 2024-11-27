package com.example.servicovenda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicovenda.model.Produto;
import com.example.servicovenda.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;


    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        Produto out = service.findById(id);
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Long> save(@RequestBody Produto produto){
        Long id = service.save(produto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Produto produto){
        service.update(produto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
