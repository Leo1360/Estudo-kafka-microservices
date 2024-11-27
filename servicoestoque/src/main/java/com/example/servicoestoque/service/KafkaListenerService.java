package com.example.servicoestoque.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.servicoestoque.exceptions.ClassNotFoundException;
import com.example.servicoestoque.model.Pedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaListenerService {
    private final PedidoProcessingService pService;


    @KafkaListener(
        topics = "dev-venda",
        groupId = "grupo-estoque",
        containerFactory = "kafkaListenerContainerStringFactory"
    )
    public void listen(String json) {
        Pedido pedido;
        try {
            pedido = new ObjectMapper().readValue(json, Pedido.class);
        } catch (JsonMappingException e) {
            throw new ClassNotFoundException(json);
        } catch (JsonProcessingException e) {
            throw new ClassNotFoundException(json);
        }
        pService.processarPedido(pedido);        
    }

    

}
