package br.com.btg.orderconsumer.service;

import br.com.btg.orderconsumer.repository.PedidoRepository;
import br.com.btg.ordershared.entity.Pedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Contém a lógica de negócio para processar e salvar os pedidos.
 */
@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final PedidoRepository pedidoRepository;
    private final ObjectMapper objectMapper;

    public OrderService(PedidoRepository pedidoRepository, ObjectMapper objectMapper) {
        this.pedidoRepository = pedidoRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Converte a mensagem JSON, calcula o valor total e salva o pedido no banco de dados.
     *
     * @param jsonMessage A mensagem do pedido em formato JSON.
     */
    public void processarPedido(String jsonMessage) {
        try {
            Pedido pedido = objectMapper.readValue(jsonMessage, Pedido.class);
            logger.info("Pedido deserializado: {}", pedido.getCodigoPedido());

            Double valorTotal = pedido.getItens().stream()
                    .mapToDouble(item -> item.getPreco() * item.getQuantidade())
                    .sum();

            pedido.setValorTotal(valorTotal);
            logger.info("Valor total calculado: {}", valorTotal);

            pedidoRepository.save(pedido);
            logger.info("Pedido salvo no banco de dados com sucesso!");

        } catch (Exception e) {
            logger.error("Erro ao processar o pedido: {}", jsonMessage, e);
        }
    }
}
