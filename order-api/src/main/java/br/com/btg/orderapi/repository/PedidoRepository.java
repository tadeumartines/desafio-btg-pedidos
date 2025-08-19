package br.com.btg.orderapi.repository;

import br.com.btg.ordershared.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, Long> {

    List<Pedido> findAllByCodigoCliente(Long codigoCliente);

    long countByCodigoCliente(Long codigoCliente);
}
