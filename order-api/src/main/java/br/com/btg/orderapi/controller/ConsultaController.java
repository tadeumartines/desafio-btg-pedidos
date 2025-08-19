package br.com.btg.orderapi.controller;

import br.com.btg.orderapi.service.ConsultaService;
import br.com.btg.ordershared.entity.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    /**
     * Endpoint para consultar o valor total de um pedido.
     */
    @GetMapping("/pedidos/{codigoPedido}/valor-total")
    public ResponseEntity<Double> consultarValorTotalPedido(@PathVariable Long codigoPedido) {
        return consultaService.consultarValorTotalPedido(codigoPedido)
                .map(ResponseEntity::ok) // Se o Optional tiver um valor, retorna 200 OK com o valor
                .orElse(ResponseEntity.notFound().build()); // Se o Optional estiver vazio, retorna 404 Not Found
    }

    /**
     * Endpoint para consultar a quantidade de pedidos por cliente.
     */
    @GetMapping("/clientes/{codigoCliente}/pedidos/quantidade")
    public ResponseEntity<Long> consultarQuantidadePedidosPorCliente(@PathVariable Long codigoCliente) {
        long quantidade = consultaService.consultarQuantidadePedidosPorCliente(codigoCliente);
        return ResponseEntity.ok(quantidade);
    }

    /**
     * Endpoint para listar os pedidos de um cliente.
     */
    @GetMapping("/clientes/{codigoCliente}/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidosPorCliente(@PathVariable Long codigoCliente) {
        List<Pedido> pedidos = consultaService.listarPedidosPorCliente(codigoCliente);
        return ResponseEntity.ok(pedidos);
    }
}
