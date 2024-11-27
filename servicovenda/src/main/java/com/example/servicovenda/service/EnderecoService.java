package com.example.servicovenda.service;

import org.springframework.stereotype.Service;

import com.example.servicovenda.exceptions.BadRequestException;
import com.example.servicovenda.model.Endereco;
import com.example.servicovenda.persistence.EnderecoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository repo;

    public Long save(Endereco endereco){
        return repo.save(endereco).getId();
    }

    public Endereco findById(Long id){
        return repo.findById(id).orElseThrow(() -> new BadRequestException("Endereco nao localizado"));
    }

    public void update(Long id, Endereco endereco){
        findById(id);
        endereco.setId(id);
        save(endereco);
    }

    public void delete(Long id){
        findById(id);
        delete(id);
    }

}
