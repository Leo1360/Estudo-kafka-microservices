package com.example.servicovenda.dto;

import java.util.List;

public record PedidoRequestDto(List<ItemPedidoRequestDto> itens, Long enderecoId) {

}
