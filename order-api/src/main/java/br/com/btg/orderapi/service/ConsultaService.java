package br.com.btg.orderapi.service;

import br.com.btg.orderapi.repository.PedidoRepository;
import br.com.btg.ordershared.entity.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final PedidoRepository pedidoRepository;

    public ConsultaService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    /**
     * Consulta o valor total de um pedido específico.
     * @param codigoPedido O código do pedido.
     * @return O valor total do pedido, ou Optional.empty() se não encontrado.
     */
    public Optional<Double> consultarValorTotalPedido(Long codigoPedido) {
        return pedidoRepository.findById(codigoPedido)
                .map(Pedido::getValorTotal);
    }

    /**
     * Consulta a quantidade de pedidos realizados por um cliente.
     * @param codigoCliente O código do cliente.
     * @return A quantidade de pedidos.
     */
    public long consultarQuantidadePedidosPorCliente(Long codigoCliente) {
        return pedidoRepository.countByCodigoCliente(codigoCliente);
    }

    /**
     * Lista todos os pedidos realizados por um cliente.
     * @param codigoCliente O código do cliente.
     * @return Uma lista de pedidos.
     */
    public List<Pedido> listarPedidosPorCliente(Long codigoCliente) {
        return pedidoRepository.findAllByCodigoCliente(codigoCliente);
    }
}
