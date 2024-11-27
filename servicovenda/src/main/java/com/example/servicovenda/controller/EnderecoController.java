package com.example.servicovenda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicovenda.model.Endereco;
import com.example.servicovenda.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService service;


    @GetMapping(path = "/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id){
        Endereco endereco = service.findById(id);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<Void> save(@RequestBody Endereco endereco){
        service.save(endereco);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
