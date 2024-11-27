# Estudo kafka

Estudo de funcionamento do funcionamento basico do Kafka. A ideia é emular um sistema de vendas e um sistema de estoque se comunicando via Kafka. 
O sistema de venda tem os itens e endereço cadastrados (de forma bem simples, pois não pe o foco), ao ser registrada a venda, uma requisição é enviada para a fila de venda, a mesma é lida pelo sistema de estoque, que roda as regras de negócio necessárias (as regras não fora implementadas por não ser o foco).

A ideia é demonstrar dois serviços se comunicando e passando um objeto na menssagem.