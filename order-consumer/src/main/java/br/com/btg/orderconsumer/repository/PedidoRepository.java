package br.com.btg.orderconsumer.repository;

import br.com.btg.ordershared.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, Long> {
}
