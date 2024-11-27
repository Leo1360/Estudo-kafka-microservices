package com.example.servicovenda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicovenda.dto.PedidoRequestDto;
import com.example.servicovenda.service.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;

    @PostMapping(path = "/")
    public ResponseEntity<Long> fazerPedido(@RequestBody PedidoRequestDto dto){
        service.finalizarPedido(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
