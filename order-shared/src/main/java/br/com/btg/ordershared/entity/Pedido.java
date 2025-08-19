package br.com.btg.ordershared.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@Document(collection = "pedidos")
public class Pedido {

    @MongoId
    private Long codigoPedido;

    @Field(name = "codigo_cliente")
    private Long codigoCliente;

    private List<Item> itens;

    private Double valorTotal;

}
