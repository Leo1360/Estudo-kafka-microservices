package com.example.servicovenda.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.servicovenda.dto.ItemPedidoRequestDto;
import com.example.servicovenda.dto.PedidoRequestDto;
import com.example.servicovenda.model.ItemPedido;
import com.example.servicovenda.model.Pedido;
import com.example.servicovenda.model.StatusPedido;
import com.example.servicovenda.persistence.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepo;
    private final ProdutoService produtoService;
    private final EnderecoService enderecoService;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void finalizarPedido(PedidoRequestDto dto){
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO_PROCESSAMENTO);
        pedido.setEnderecoEntrega(enderecoService.findById(dto.enderecoId()));
        
        List<ItemPedido> itemList = dto.itens().stream().map(this::itemDtoToItemPedido).collect(Collectors.toList());
        
        pedido.setItems(itemList);
        save(pedido);
        senviarEventoDeVendaEstoque(pedido);
    }

    public void save(Pedido pedido) {
        pedidoRepo.save(pedido);
    }

    private void senviarEventoDeVendaEstoque(Pedido pedido) {
        kafkaTemplate.send("dev-venda",pedido);
    }

    private ItemPedido itemDtoToItemPedido(ItemPedidoRequestDto dto){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produtoService.findById(dto.prodId()));
        itemPedido.setQnt(dto.qnt());
        itemPedido.setSubTotal(itemPedido.getProduto().getPreco().multiply(BigDecimal.valueOf(dto.qnt())));
        return itemPedido;
    }

}
